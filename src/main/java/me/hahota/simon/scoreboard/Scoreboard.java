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
import org.slf4j.Logger;

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
    public @NotNull Logger getSLF4JLogger() {
        Logger 1 = null;
        return 1;
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
            Objective obj = board.registerNewObjective("HahotaScoreboard", "dummy",
                    ChatColor.translateAlternateColorCodes('&', "&2Hahota"));
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score = obj.getScore(ChatColor.AQUA + "What");
        ((Score) score).setScore(4);
            Score score2 = obj.getScore(ChatColor.GREEN + "ever");
            ((Score) score2).setScore(3);
            Score score3 = obj.getScore(ChatColor.RED + "this");
            ((Score) score3).setScore(2);
            Score score4 = obj.getScore(ChatColor.AQUA + "is");
            ((Score) score4).setScore(1);
            Score score5 = obj.getScore(ChatColor.BLACK + "lol");
            ((Score) score5).setScore(0);


            player.setScoreboard(board);

        }
}
