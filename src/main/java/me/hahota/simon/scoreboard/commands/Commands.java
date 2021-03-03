package me.hahota.simon.scoreboard.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (!p.hasPermission("hahota.board")){
            p.sendMessage("§4! §cPermission");
            return true;
        }else {
            if (args.length == 0){

                p.sendMessage("§aCommands");
                p.sendMessage("§a/hscore toggle");
                p.sendMessage("§a/hscore about");
                return true;
            }
            if (args[0].equalsIgnoreCase("toggle")){
                if (args.length == 1){
                    p.sendMessage("§a/hscore toggle");
                    return true;
                }
                //Stuff
            }
            if (args[0].equalsIgnoreCase("about")){
                if (args.length == 1){
                    p.sendMessage("§a/hscore about");
                    return true;
                }
                p.sendMessage("Hahota board ${project.version}");
            }
        }
        return false;
    }

}
