package me.hahota.simon.scoreboard;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.ScoreboardManager;
import org.jetbrains.annotations.NotNull;

public final class Scoreboard extends JavaPlugin implements @NotNull Listener {
    private static Economy econ = null; // vault economy
    private static Chat chat = null; // vault prefix (? chat)

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

    @Override
    public void onDisable() {

        // Plugin shutdown logic
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        createBoard(event.getPlayer());

    }

    public void createBoard(){
        manager = Bukkit.getScoreboardManager();
        board = manager.getMainScoreboard();
        Objective obj = board.registerNewObjective("ScoreboardAc", "dummy",
                ChatColor.translateAlternateColorCodes('&', "   &2Hahota    "));
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score = obj.getScore("");
        score.setScore(1);
        Score score2 = obj.getScore(ChatColor.GRAY + "Your rank: " + getChat().getPlayerPrefix(player)); // add luckperms api check for rank
        score2.setScore(2);
        Score score3 = obj.getScore(" ");
        score3.setScore(3);
        Score score4 = obj.getScore(ChatColor.GRAY + "Your balance:" + getEcon().getBalance(player)); // add vault api to check for da balance
        score4.setScore(4);
        Score score5 = obj.getScore("");
        score5.setScore(5);


        player.setScoreboard(board);

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
        chat = rsp.getProvider();
        return chat != null;
    }

    public static Economy getEcon() {
        return econ;
    }

    public static Chat getChat() {
        return chat;
    }
}
