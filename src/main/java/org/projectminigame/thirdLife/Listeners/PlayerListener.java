package org.projectminigame.thirdLife.Listeners;

import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.projectminigame.thirdLife.ThirdLife;

public class PlayerListener implements Listener {
    private final ThirdLife thirdLife;
    
    public PlayerListener(ThirdLife plugin) {
        this.thirdLife = plugin;
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        
        /*
            DEBUG & TESTING
            Use command: /debug <enable | disable>
         */
        if (thirdLife.DEBUG_ENABLED) {
            player.setStatistic(Statistic.DEATHS, 0);
            this.thirdLife.logger.info("DEBUG: " + player.getName() + "'s Death Stat set to 0");
            player.sendMessage("Debug mode is enabled");
        }
        
        this.thirdLife.getNametagManager().createScoreboardAndSetNameTags(player);
        this.thirdLife.getNametagManager().handleAssignNametagColor(player);
    }
    
    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        
        // Runnables required for a slight delay here
        new BukkitRunnable() {
            @Override
            public void run() {
                thirdLife.getNametagManager().removePlayerNametagFromScoreboard(player);
            }
        }.runTaskLater(thirdLife, 1);
        
        new BukkitRunnable() {
            @Override
            public void run() {
                thirdLife.getNametagManager().handleAssignNametagColor(player);
            }
        }.runTaskLater(thirdLife, 2);
    }
    
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() {
                thirdLife.getNametagManager().removePlayerNametagFromScoreboard(event.getPlayer());
            }
        }.runTaskLater(thirdLife, 1);
    }
}
