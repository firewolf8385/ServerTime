package org.firewolf8385.servertime;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class Settings
{
    Plugin p;
    static Settings instance = new Settings();
    private Settings() { }

    /**
     * Get the config file.
     * @return config
     */
    public FileConfiguration getConfig()
    {
        return p.getConfig();
    }

    /**
     * Retrieves the Instance of SettingsManager
     * @return Instance
     */
    public static Settings getInstance()
    {
        return instance;
    }

    /**
     * Creates the files if they do not exist.
     * @param p plugin
     */
    public void setup(Plugin p)
    {
        this.p = p;

        // Create the Plugin Folder if it does not exist.
        if (!p.getDataFolder().exists())
        {
            p.getDataFolder().mkdir();
        }

        p.getConfig().options().copyDefaults(true);
        p.saveDefaultConfig();
    }

    /**
     * Save the config file.
     */
    public void saveConfig()
    {
        p.saveConfig();
    }

    /**
     * Reload the config file.
     */
    public void reloadConfig()
    {
        p.saveConfig();
        p.getConfig();
    }

}
