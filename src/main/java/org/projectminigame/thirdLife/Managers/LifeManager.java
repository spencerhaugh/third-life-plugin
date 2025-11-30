package org.projectminigame.thirdLife.Managers;

import org.bukkit.entity.Player;
import org.projectminigame.thirdLife.ThirdLife;
import org.projectminigame.thirdLife.utils.StatsUtil;

public class LifeManager {
    
    // Variables
    private final StatsUtil statsUtil;
    
    // Constructor
    public LifeManager(ThirdLife plugin) {
        this.statsUtil = new StatsUtil();
    }
    
    /*
        Get Lives
     */
    public int getDeathCount(Player player) {
        return statsUtil.getPlayerDeathCount(player);
    }
    
}
