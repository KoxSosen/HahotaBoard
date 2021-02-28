package me.hahota.simon.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.ScoreboardManager;
import org.jetbrains.annotations.NotNull;

public final class Scoreboard extends JavaPlugin implements @NotNull Listener {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this,this);
        // Plugin startup logic

        if (!Bukkit.getOnlinePlayers().isEmpty())
            for (Player online : Bukkit.getOnlinePlayers())
                createBoard(online);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

        @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        createBoard(event.getPlayer());

        }

        public void createBoard(Player player) {
            ScoreboardManager manager = Bukkit.getScoreboardManager();
            org.bukkit.scoreboard.Scoreboard board = manager.getMainScoreboard();
            Objective obj = board.registerNewObjective("eztneadd", "dummy",
                    ChatColor.translateAlternateColorCodes('&', "   &2Hahota    "));
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score = obj.getScore("");
        score.setScore(1);
            Score score2 = obj.getScore(ChatColor.GRAY + "Your rank: ");
            score2.setScore(2);
            Score score3 = obj.getScore("");
            score3.setScore(3);
            Score score4 = obj.getScore(ChatColor.GRAY + "Your balance:");
            score4.setScore(4);
            Score score5 = obj.getScore("");
            score5.setScore(5);


            player.setScoreboard(board);

        }
}
