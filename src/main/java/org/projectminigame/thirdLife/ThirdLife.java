package org.projectminigame.thirdLife;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.projectminigame.thirdLife.Commands.DebugCommand;
import org.projectminigame.thirdLife.Commands.SetLivesCommand;
import org.projectminigame.thirdLife.Listeners.PlayerListener;
import org.projectminigame.thirdLife.Managers.LifeManager;
import org.projectminigame.thirdLife.Managers.NametagManager;

import java.util.logging.Logger;

public final class ThirdLife extends JavaPlugin {
    public Logger logger = Logger.getLogger("Third Life");
    
    public boolean DEBUG_ENABLED = false;
    
    /*
        THIRD LIFE
        - Players have 3 lives until they are forced into spectator mode only
        - Nametag displays player name in Green, Yellow or Red according to lives left
     */
    
    // Managers
    private LifeManager lifeManager;
    private NametagManager nametagManager;
    
    @Override
    public void onEnable() {
        
        // Plugin Manager
        PluginManager pluginManager = Bukkit.getPluginManager();
        
        // Listeners
        pluginManager.registerEvents(new PlayerListener(this), this);
        
        // Commands
        getCommand("debug").setExecutor(new DebugCommand(this));
        getCommand("setlives").setExecutor(new SetLivesCommand());
        
        // Manager instances
        this.lifeManager = new LifeManager(this);
        this.nametagManager = new NametagManager(this);
    }
    
    // Getters
    public LifeManager getLifeManager() {
        return lifeManager;
    }
    
    public NametagManager getNametagManager() {
        return nametagManager;
    }
    
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
