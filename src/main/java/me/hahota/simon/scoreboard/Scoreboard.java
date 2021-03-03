package me.hahota.simon.scoreboard;

import com.moandjiezana.toml.Toml;
import me.hahota.simon.scoreboard.commands.Commands;
import me.hahota.simon.scoreboard.listeners.PlayerJoin;
import me.hahota.simon.scoreboard.utils.BoardManager;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

public final class Scoreboard extends JavaPlugin {

    private static Economy econ = null; // vault economy
    private static Chat chat = null; // vault prefix (? chat)
    private static Scoreboard instance;

    private BoardManager boardManager = new BoardManager();

    @Override
    public void onEnable() {
        instance = this;

        if (!setupEconomy()) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupChat();
        boardManager.createBoard();

        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
        getCommand("hscore").setExecutor(new Commands());
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        assert rsp != null;
        chat = rsp.getProvider();
        return chat != null;
    }

    public static Economy getEcon() {
        return econ;
    }

    public static Chat getChat() {
        return chat;
    }

    public String format(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static Scoreboard getInstance() {
        return instance;
    }

    public BoardManager getBoardManager() {
        return boardManager;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Toml loadConfig() {
        if(!getDataFolder().exists()) {
            getDataFolder().mkdirs(); // Creating the directory as it may not exist
        }
        File file = new File(getDataFolder(), "config.toml"); // Assign a variable to the file
        if(!file.exists()) {
            try {
                Files.copy(Objects.requireNonNull(getResource("config.toml")), file.toPath());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return new Toml(
                new Toml().read(getResource("config.toml")))
                .read(file);
    }

    public class TomlConfig {

        public String Title;
        public String Score1;
        private String Score2;
        private String Score3;
        private String Score4;
        private String Score5;
        private String Vault;
        private String Papi;

    }

    public void applyToClass(Toml toml) {
        toml.to(TomlConfig.class);
    }
}
