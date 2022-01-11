package com.mc.hypernite.playerprofile.manager;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class ConfigManager {
    private static ConfigManager configManager;
    private File configFile;
    private FileConfiguration configuration;

    //Configuration Extraction
    public static String serverName, host, database, username, password;
    public static boolean isPluginEnable, isMysqlSSLEnable;
    public static int port, syncInterval;

    private ConfigManager(Plugin plugin) {
        configFile = new File(plugin.getDataFolder(), "config.yml");
        if(!configFile.exists()) {
            plugin.saveResource("config.yml", true);
        }
        configuration = YamlConfiguration.loadConfiguration(configFile);
    }

    public static ConfigManager getInstance(Plugin plugin) {
        if(configManager == null) {
            configManager = new ConfigManager(plugin);
        }
        return configManager;
    }

    public void loadConfig() {
        serverName = configuration.getString("settings.servername");
        isPluginEnable = configuration.getBoolean("settings.plugin-enable");
        syncInterval = configuration.getInt("settings.sync-interval");
        host = configuration.getString("mysql.host");
        database = configuration.getString("mysql.database");
        username = configuration.getString("mysql.username");
        password = configuration.getString("mysql.password");
        port = configuration.getInt("mysql.port");
        isMysqlSSLEnable = configuration.getBoolean("mysql.use-ssl");
    }
}
