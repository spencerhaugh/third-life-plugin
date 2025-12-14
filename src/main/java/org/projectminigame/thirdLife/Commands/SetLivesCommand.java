package org.projectminigame.thirdLife.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.projectminigame.thirdLife.ThirdLife;

public class SetLivesCommand implements CommandExecutor {
    ThirdLife thirdLife;
    
    public SetLivesCommand(ThirdLife thirdLife){
        this.thirdLife = thirdLife;
    }
    
    @Override
    public boolean onCommand(
        @NotNull CommandSender commandSender,
        @NotNull Command command,
        @NotNull String s,
        @NotNull String @NotNull [] args
    ) {
        if (commandSender instanceof Player player) {
            return execute(player, commandSender, args);
        }
        return false;
    }

    public boolean execute(Player player, CommandSender commandSender, String[] args) {
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
                this.thirdLife.getLifeManager().setDeaths(target, 3);
                break;
            case 1:
                this.thirdLife.getLifeManager().setDeaths(target, 2);
                break;
            case 2:
                this.thirdLife.getLifeManager().setDeaths(target, 1);
                break;
            case 3:
                this.thirdLife.getLifeManager().setDeaths(target, 0);
                break;
            default:
                player.sendMessage(ChatColor.RED + "Error: Out of bounds! Set lives to 1, 2, 3 or 0.");
                return false;
        }
    
        livesResetMessage(player, target, lives);
        
        this.thirdLife.getNametagManager().removePlayerNametagFromScoreboard(target);
        this.thirdLife.getNametagManager().handleAssignNametagColor(target);
            return false;
    }

    public void livesResetMessage(Player player, Player target, int lives) {
        player.sendMessage(ChatColor.GRAY + target.getName() + "'s remaining lives have been updated to " + lives);
        target.sendMessage(ChatColor.GRAY + "Your remaining lives have been updated by the Admin");
    }
}
