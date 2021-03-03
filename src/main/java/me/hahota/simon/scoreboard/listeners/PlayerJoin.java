package me.hahota.simon.scoreboard.listeners;

import me.hahota.simon.scoreboard.Scoreboard;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        Scoreboard.getInstance().getBoardManager().setupBoard(p);
        Scoreboard.getInstance().getBoardManager().setupBoard(p);
    }
}
