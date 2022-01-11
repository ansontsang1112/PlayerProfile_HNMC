package com.mc.hypernite.playerprofile.database;

import com.mc.hypernite.playerprofile.main.PlayerProfile;
import com.mc.hypernite.playerprofile.manager.ConfigManager;
import com.mc.hypernite.playerprofile.utils.Utils;
import org.bukkit.ChatColor;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataSourcesManager {
    static String jdbcURL = "jdbc:mysql://" + ConfigManager.host +  ":" + ConfigManager.port +
            "/" + ConfigManager.database + "?useSSL=false&autoReconnect=true&useUnicode=true&characterEncoding=UTF-8";


    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, ConfigManager.username, ConfigManager.password);
            return connection;
        } catch (Exception e) {
            PlayerProfile.getPlugin(PlayerProfile.class).getLogger().info(Utils.prefix + " | " + ChatColor.RED + e.getMessage());
            return null;
        }
    }
}
