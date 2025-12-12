package org.projectminigame.thirdLife.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.projectminigame.thirdLife.ThirdLife;

public class DebugCommand implements CommandExecutor {
    
    private static ThirdLife thirdLife;
    
    public enum DebugState {
        ENABLE,
        DISABLE
    }
    
    public DebugCommand(ThirdLife plugin) {
        thirdLife = plugin;
    }
    
    public boolean onCommand(
        @NotNull CommandSender commandSender,
        @NotNull Command command,
        @NotNull String s,
        @NotNull String @NotNull [] args
    ) {
        if (commandSender instanceof Player player) {
            return execute(player, args);
        }
        return false;
    }
    
    public boolean execute(Player player, String[] args) {
        if (!player.isOp()) {
            player.sendMessage(
                ChatColor.RED +
                "You must be op to use this command."
            );
            return false;
        }
        
        if (args.length != 1) {
            usage(player);
            return false;
        }
        
        if (args[0].equalsIgnoreCase(DebugState.ENABLE.toString())) {
            thirdLife.DEBUG_ENABLED = true;
            player.sendMessage("Debug enabled. Deaths reset on each login");
        }
        else if (args[0].equalsIgnoreCase(DebugState.DISABLE.toString())) {
            thirdLife.DEBUG_ENABLED = false;
            player.sendMessage("Debug disabled. Regular gameplay mode ON.");
        }
        else {
            usage(player);
        }
        return false;
    }
    
    public void usage(Player player) {
        player.sendMessage("Usage: /debug <enable | disable>");
    }
}
