package me.hahota.simon.scoreboard;

import com.moandjiezana.toml.Toml;
import me.clip.placeholderapi.PlaceholderAPI;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.ScoreboardManager;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public final class Scoreboard extends JavaPlugin implements @NotNull Listener {

    private static Economy econ = null; // vault economy
    private static Chat chat = null; // vault prefix (? chat)
    private ScoreboardManager manager; // create scoreboard on startup
    private org.bukkit.scoreboard.Scoreboard board;
    private Objective obj;

    @Override
    public void onEnable() {


        this.getServer().getPluginManager().registerEvents(this, this);
        // Plugin startup logic

        if (!setupEconomy()) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupChat();
        createBoard();

    }




    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Toml loadConfig() {
        if(!getDataFolder().exists()) {
            getDataFolder().mkdirs(); // Creating the directory as it may not exist
        }
        File file = new File(getDataFolder(), "config.toml"); // Assign a variable to the file
        if(!file.exists()) {
            try {
                Files.copy(getResource("config.toml"), file.toPath());
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

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        p.setScoreboard(board);
        setupBoard(p);
        updateBoard(p);
    }

    public void createBoard() {
        manager = Bukkit.getScoreboardManager();
        board = manager.getNewScoreboard();
        obj = board.registerNewObjective("asdf", "dummy",
                ChatColor.translateAlternateColorCodes('&', "   &aHahota    ")); // config.getString(Title);
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    public void setupBoard(Player p) {
        obj.getScore(format("")).setScore(1);
        obj.getScore(format("&3Balance: ") + getEcon().getBalance(p)).setScore(2);
        obj.getScore(format("")).setScore(3);
        obj.getScore(format("&3Prefix: ") + getChat().getPlayerPrefix(p)).setScore(4);
        obj.getScore(" ").setScore(5);
    }

    public void updateBoard(Player p) {
        new BukkitRunnable() {
            @Override
            public void run() {
                obj.getScore(format("")).setScore(1);
                obj.getScore(format("&3Balance: ") + getEcon().getBalance(p)).setScore(2);
                obj.getScore(format("")).setScore(3);
                obj.getScore(format("&3Prefix: ") + getChat().getPlayerPrefix(p)).setScore(4);
                obj.getScore(" ").setScore(5);
            }
        }.runTaskTimer(this, 600, 1);
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

    private String format(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
