package org.projectminigame.thirdLife.Managers;

import org.bukkit.entity.Player;
import org.projectminigame.thirdLife.utils.StatsUtil;

public class LifeManager {
    public static int getDeathCount(Player player) {
        return StatsUtil.getPlayerDeathCount(player);
    }
}
