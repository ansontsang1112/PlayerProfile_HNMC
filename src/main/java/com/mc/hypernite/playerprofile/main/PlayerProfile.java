package com.mc.hypernite.playerprofile.main;

import com.mc.hypernite.playerprofile.database.DataSourcesManager;
import com.mc.hypernite.playerprofile.database.DatabaseName;
import com.mc.hypernite.playerprofile.database.TableManager;
import com.mc.hypernite.playerprofile.manager.ConfigManager;
import com.mc.hypernite.playerprofile.utils.ProfileController;
import com.mc.hypernite.playerprofile.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.TreeMap;
import java.util.UUID;

public final class PlayerProfile extends JavaPlugin {

    private Plugin plugin;
    private Connection connection;
    public static TreeMap<UUID, ProfileController> playerProfileMapping;

    @Override
    public void onEnable() {
        //Initiation
        plugin = this;

        //Load Configuration
        ConfigManager.getInstance(this).loadConfig();

        //Check if plugin initiated
        if(!ConfigManager.isPluginEnable) {
            this.getLogger().info(Utils.prefix + " | " + ChatColor.RED + " Please enable and config the plugin and mysql information. Restart Server after enabling the plugin.");
            onDisable();
        } else {
            //Check Connection
            connection = DataSourcesManager.getConnection();

            //Table Initialization
            if(!new TableManager(connection).tableInitialization()) {
                this.getLogger().info(Utils.prefix + " | " + ChatColor.RED + " Tables (DB) fail to initialized! Please check the database configuration.");
                return;
            }

            //Plugin Loaded Successfully
            this.getLogger().info(Utils.prefix + " | " + ChatColor.GREEN + " Plugin loaded Successfully");
        }
    }

    @Override
    public void onDisable() {
        this.getLogger().info(Utils.prefix + " | " + ChatColor.RED + " Plugin unloaded Successfully");
    }
}
