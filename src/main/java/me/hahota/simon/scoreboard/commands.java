package me.hahota.simon.scoreboard;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

public class commands {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("hscore")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage("Hahota board ${project.version}");
            }
            else {
                sender.sendMessage("Hahota board ${project.version}");
                return true;

            }
        }
        return false;
    }
    public boolean onCommand1(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("hscore toggle")) {
            if (sender.hasPermission("hscore.toggle"))
            if (sender instanceof Player    ) {
                Player player = (Player) sender; // toggle scoreboard
            }
            else {
                sender.sendMessage("Hahota board ${project.version}");
                return true;
            }
        }
        return false;
    }






}
