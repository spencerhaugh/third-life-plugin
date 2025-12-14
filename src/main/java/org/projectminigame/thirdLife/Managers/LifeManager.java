package org.projectminigame.thirdLife.Managers;

import org.bukkit.GameMode;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.projectminigame.thirdLife.utils.StatsUtil;

public class LifeManager {
    public int getDeathCount(Player player) {
        return StatsUtil.getPlayerDeathCount(player);
    }
    
    public void setDeaths(Player target, int deaths) {
        target.setStatistic(Statistic.DEATHS, deaths);
        updateGameModeOnDeath(target);
    }
    
    public void updateGameModeOnDeath(Player target) {
        int deaths = getDeathCount(target);
        
        if (deaths >= 3 && target.getGameMode() == GameMode.SURVIVAL) {
            target.setGameMode(GameMode.SPECTATOR);
        } else if (deaths < 3 && target.getGameMode() != GameMode.SURVIVAL){
            target.setGameMode(GameMode.SURVIVAL);
        }
    }
}
