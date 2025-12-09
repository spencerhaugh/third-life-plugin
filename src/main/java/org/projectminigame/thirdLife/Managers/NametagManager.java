package org.projectminigame.thirdLife.Managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;
import org.projectminigame.thirdLife.utils.NameColors;

public class NametagManager {
    
    static String greenMessage = ChatColor.GREEN + "You have 3 lives. Stay alive!";
    static String yellowMessage = ChatColor.YELLOW + "You are on your Yellow life. Be careful.";
    static String redMessage = ChatColor.RED + "You are on your RED life. You can now kill other players.";
    static String eliminatedMessage = ChatColor.GRAY + "You ran out of lives.";
    
    public static void createScoreboardAndSetNameTags(Player player) {
        player.setScoreboard(
            Bukkit.getScoreboardManager().getNewScoreboard()
        );
        
        for (NameColors color : NameColors.values()) {
            Team team = player.getScoreboard().registerNewTeam(color.name());
            team.setColor(color.getNameColor());
        }
    }
    
    public static void handleAssignNametagColor(Player player) {
        if (player == null) {
            return;
        }
        
        int playerDeaths = LifeManager.getDeathCount(player);
        
        if (playerDeaths >= 3) {
            playerOutOfLives(player);
            assignPlayerTeamColor(player, NameColors.GRAY);
            return;
        }
        
        switch (playerDeaths) {
            case 0:
                assignPlayerTeamColor(player, NameColors.GREEN);
                player.sendMessage(greenMessage);
                break;
                
            case 1:
                assignPlayerTeamColor(player, NameColors.YELLOW);
                player.sendMessage(yellowMessage);
                break;
                
            case 2:
                assignPlayerTeamColor(player, NameColors.RED);
                player.sendMessage(redMessage);
                break;
        }
    }
    
    private static void playerOutOfLives(Player player) {
            player.setGameMode(GameMode.SPECTATOR);
            player.sendMessage(eliminatedMessage);
    }
    
    private static void assignPlayerTeamColor(Player player, NameColors color) {
        for (Player target : Bukkit.getOnlinePlayers()) {
            target.getScoreboard()
                .getTeam(color.name())
                .addEntry(player.getName());
        }
    }
    
    public static void removePlayerNametagFromScoreboard(Player player) {
        for (Player target : Bukkit.getOnlinePlayers()) {
                target.getScoreboard()
                    .getEntryTeam(player.getName())
                    .removeEntry(player.getName());
        }
    }
}
