package com.mc.hypernite.playerprofile.main;

import com.mc.hypernite.playerprofile.manager.ConfigManager;
import com.mc.hypernite.playerprofile.utils.ProfileController;
import com.mc.hypernite.playerprofile.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.TreeMap;
import java.util.UUID;

public final class PlayerProfile extends JavaPlugin {

    public Plugin plugin;
    public TreeMap<UUID, ProfileController> playerProfileMapping;

    @Override
    public void onEnable() {
        //Initiation
        plugin = this;

        //Load Configuration
        ConfigManager.getInstance(this).loadConfig();

        //Plugin Loaded Successfully
        this.getLogger().info(Utils.prefix + " | " + ChatColor.GREEN + " Plugin loaded Successfully");

    }

    @Override
    public void onDisable() {
        this.getLogger().info(Utils.prefix + " | " + ChatColor.RED + " Plugin unloaded Successfully");
    }
}
