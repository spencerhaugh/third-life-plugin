package org.projectminigame.thirdLife.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.projectminigame.thirdLife.Managers.NametagManager;

public class SetLivesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(
        @NotNull CommandSender commandSender,
        @NotNull Command command,
        @NotNull String s,
        @NotNull String @NotNull [] args
    ) {
        if (commandSender instanceof Player player) {
            if (!player.isOp()) {
                player.sendMessage(
                ChatColor.RED +
                    "You must be op to use this command."
                );
                return false;
            }
            
            if (args.length != 2) {
                commandSender.sendMessage(
                ChatColor.GRAY +
                    "Error: Usage: /setlives <player> <number>"
                );
                return false;
            }
            
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                commandSender.sendMessage(
                ChatColor.RED + "Error: Player not found."
                );
                return false;
            }
            
            int lives = Integer.parseInt(args[1]);
            
            switch (lives) {
                case 0:
                    target.setStatistic(Statistic.DEATHS, 3);
                    break;
                case 1:
                    target.setStatistic(Statistic.DEATHS, 2);
                    break;
                case 2:
                    target.setStatistic(Statistic.DEATHS, 1);
                    break;
                case 3:
                    target.setStatistic(Statistic.DEATHS, 0);
                    break;
                default:
                    player.sendMessage(ChatColor.RED + "Error: Out of bounds! Set lives to 1, 2, 3 or 0.");
                    return false;
            }
            
            livesResetMessage(player, target, lives);
            
            NametagManager.removePlayerNametagFromScoreboard(target);
            NametagManager.handleAssignNametagColor(target);
        }
        return false;
    }
    
    public static void livesResetMessage(Player player, Player target, int lives) {
        player.sendMessage(ChatColor.GRAY + target.getName() + "'s remaining lives have been updated to " + lives);
        target.sendMessage(ChatColor.GRAY + "Your remaining lives have been updated by the Admin");
    }
}
