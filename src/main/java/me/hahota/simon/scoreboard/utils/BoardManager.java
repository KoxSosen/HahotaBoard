package me.hahota.simon.scoreboard.utils;

import me.hahota.simon.scoreboard.Scoreboard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.ScoreboardManager;

public class BoardManager {

    private ScoreboardManager manager; // create scoreboard on startup
    private org.bukkit.scoreboard.Scoreboard board;
    private Objective obj;

    public void createBoard() {
        manager = Bukkit.getScoreboardManager();
        board = manager.getNewScoreboard();
        obj = board.registerNewObjective("Board", "dummy",
                ChatColor.translateAlternateColorCodes('&', Scoreboard.getInstance().getConfig().getString("Title"))); // config.getString(Title);
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    public void setupBoard(Player p) {
        obj.getScore(Scoreboard.getInstance().format("")).setScore(1);
        obj.getScore(Scoreboard.getInstance().format("&3Balance: ") + Scoreboard.getEcon().getBalance(p)).setScore(2);
        obj.getScore(Scoreboard.getInstance().format("")).setScore(3);
        obj.getScore(Scoreboard.getInstance().format("&3Prefix: ") + Scoreboard.getChat().getPlayerPrefix(p)).setScore(4);
        obj.getScore(" ").setScore(5);
    }

    public void updateBoard(Player p) {
        new BukkitRunnable() {
            @Override
            public void run() {
                obj.getScore(Scoreboard.getInstance().format("")).setScore(1);
                obj.getScore(Scoreboard.getInstance().format("&3Balance: ") + Scoreboard.getEcon().getBalance(p)).setScore(2);
                obj.getScore(Scoreboard.getInstance().format("")).setScore(3);
                obj.getScore(Scoreboard.getInstance().format("&3Prefix: ") + Scoreboard.getChat().getPlayerPrefix(p)).setScore(4);
                obj.getScore(" ").setScore(5);
            }
        }.runTaskTimer(Scoreboard.getInstance(), 600, 1);
    }
}
