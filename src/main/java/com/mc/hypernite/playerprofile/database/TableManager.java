package com.mc.hypernite.playerprofile.database;

import com.mc.hypernite.playerprofile.main.PlayerProfile;
import com.mc.hypernite.playerprofile.utils.Utils;
import org.bukkit.ChatColor;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TableManager {
    private Connection connection;
    static ArrayList<DatabaseName> databaseNames = new ArrayList<>();

    public TableManager(Connection connection) {
        this.connection = connection;

        this.databaseNames.add(DatabaseName.GENERAL_PROFILE);
        this.databaseNames.add(DatabaseName.SW_DATA);
        this.databaseNames.add(DatabaseName.COMMAND_RECORD);
        this.databaseNames.add(DatabaseName.DEATH_RECORD);
        this.databaseNames.add(DatabaseName.MESSAGE_RECORD);
    }

    private boolean isTableExist(DatabaseName tableName) {
        try {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[]{tableName.label});
            while (resultSet.next()) {
                return true;
            }
            resultSet.close();
        } catch (SQLException e) {
            return false;
        }
        return false;
    }

    private boolean createTable(String statement) {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(statement);
            stmt.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean tableInitialization() {
        boolean isSuccess = true;

        for(DatabaseName tableName : databaseNames) {
            if(!isTableExist(tableName)) {
                if(createTable(tableName.sql)) {
                    PlayerProfile.getPlugin(PlayerProfile.class).getLogger().info(Utils.prefix + " | " + ChatColor.AQUA + " Table " + tableName.label + " created successfully.");
                } else {
                    isSuccess = false;
                    PlayerProfile.getPlugin(PlayerProfile.class).getLogger().info(Utils.prefix + " | " + ChatColor.RED + " Table " + tableName.label + " fail to create.");
                }
            }
        }

        if(!isSuccess) return false;

        PlayerProfile.getPlugin(PlayerProfile.class).getLogger().info(Utils.prefix + " | " + ChatColor.GREEN + " Table initialized successfully.");
        return true;
    }
}