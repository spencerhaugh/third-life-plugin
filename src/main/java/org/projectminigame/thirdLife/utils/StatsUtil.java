package org.projectminigame.thirdLife.utils;

import org.bukkit.Statistic;
import org.bukkit.entity.Player;

public class StatsUtil {
    public static int getPlayerDeathCount(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        
        return player.getStatistic(Statistic.DEATHS);
    }
}
