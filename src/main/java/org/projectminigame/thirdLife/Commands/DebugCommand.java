package org.projectminigame.thirdLife.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.projectminigame.thirdLife.ThirdLife;

public class DebugCommand implements CommandExecutor {
    
    private static ThirdLife thirdLife;
    
    public DebugCommand(ThirdLife plugin) {
        thirdLife = plugin;
    }
    
    public boolean onCommand(
        @NotNull CommandSender commandSender,
        @NotNull Command command,
        @NotNull String s,
        @NotNull String @NotNull [] args
    ) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            
            if (args.length != 1) {
                player.sendMessage("Usage: /debug <enable | disable>");
            }
            
            if (args[0].equalsIgnoreCase("enable")) {
                thirdLife.DEBUG_ENABLED = true;
                player.sendMessage("Debug enabled. Deaths reset on each login");
            } else if (args[0].equalsIgnoreCase("disable")) {
                thirdLife.DEBUG_ENABLED = false;
                player.sendMessage("Debug disabled");
            }
        }
        
        return false;
    }
}
