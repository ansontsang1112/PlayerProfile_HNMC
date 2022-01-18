package com.mc.hypernite.playerprofile.main;

import com.mc.hypernite.playerprofile.database.DataSourcesManager;
import com.mc.hypernite.playerprofile.database.DatabaseName;
import com.mc.hypernite.playerprofile.database.TableManager;
import com.mc.hypernite.playerprofile.listener.PlayerCommandListener;
import com.mc.hypernite.playerprofile.listener.PlayerInOutListener;
import com.mc.hypernite.playerprofile.listener.PlayerProfileListener;
import com.mc.hypernite.playerprofile.manager.ConfigManager;
import com.mc.hypernite.playerprofile.manager.ProfileDataManager;
import com.mc.hypernite.playerprofile.utils.ProfileController;
import com.mc.hypernite.playerprofile.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public final class PlayerProfile extends JavaPlugin {

    public Plugin plugin;
    public Connection connection;
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

            //Register Listener
            this.getServer().getPluginManager().registerEvents(new PlayerProfileListener(), this);
            this.getServer().getPluginManager().registerEvents(new PlayerInOutListener(), this);
            this.getServer().getPluginManager().registerEvents(new PlayerCommandListener(), this);

            //Plugin Loaded Successfully
            this.getLogger().info(Utils.prefix + " | " + ChatColor.GREEN + " Plugin loaded Successfully");

            //Sync Data to Database within a period of time
            Bukkit.getScheduler().runTaskTimer(plugin, () -> {
                //Get TreeMap data and try to sync


            }, 20L * 60, 20L * ConfigManager.syncInterval); //Sync after the plugin run 1 min and each "interval" seconds.
        }
    }

    @Override
    public void onDisable() {
        this.getLogger().info(Utils.prefix + " | " + ChatColor.RED + " Plugin unloaded Successfully");
    }
}
