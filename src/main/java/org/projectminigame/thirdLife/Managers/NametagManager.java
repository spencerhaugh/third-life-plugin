package org.projectminigame.thirdLife.Managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;
import org.projectminigame.thirdLife.ThirdLife;
import org.projectminigame.thirdLife.utils.NameColors;

public class NametagManager {
    ThirdLife thirdLife;
    
    static String greenMessage = ChatColor.GREEN + "You have 3 lives. Stay alive!";
    static String yellowMessage = ChatColor.YELLOW + "You are on your Yellow life. Be careful.";
    static String redMessage = ChatColor.RED + "You are on your RED life. You can now kill other players.";
    static String eliminatedMessage = ChatColor.GRAY + "You ran out of lives.";
    
    public NametagManager(ThirdLife plugin) {
        this.thirdLife = plugin;
    }
    
    public void createScoreboardAndSetNameTags(Player player) {
        player.setScoreboard(
            Bukkit.getScoreboardManager().getNewScoreboard()
        );
        
        for (NameColors color : NameColors.values()) {
            Team team = player.getScoreboard().registerNewTeam(color.name());
            team.setColor(color.getNameColor());
        }
    }
    
    public void handleAssignNametagColor(Player player) {
        if (player == null) {
            return;
        }
        
        int playerDeaths = thirdLife.getLifeManager().getDeathCount(player);
        
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
    
    private void playerOutOfLives(Player player) {
        this.thirdLife.getLifeManager().updateGameModeOnDeath(player);
        player.sendMessage(eliminatedMessage);
    }
    
    private void assignPlayerTeamColor(Player player, NameColors color) {
        for (Player target : Bukkit.getOnlinePlayers()) {
            target.getScoreboard()
                .getTeam(color.name())
                .addEntry(player.getName());
        }
    }
    
    public void removePlayerNametagFromScoreboard(Player player) {
        for (Player target : Bukkit.getOnlinePlayers()) {
                target.getScoreboard()
                    .getEntryTeam(player.getName())
                    .removeEntry(player.getName());
        }
    }
}
