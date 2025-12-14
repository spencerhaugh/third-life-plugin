package org.projectminigame.thirdLife.utils;

import org.bukkit.ChatColor;

public enum NameColors {
    GREEN(ChatColor.GREEN),
    YELLOW(ChatColor.YELLOW),
    RED(ChatColor.RED),
    GRAY(ChatColor.GRAY);
    
    private ChatColor color;
    
    NameColors(ChatColor color) {
        this.color = color;
    }
    
    public ChatColor getNameColor() {
        return color;
    }
}
