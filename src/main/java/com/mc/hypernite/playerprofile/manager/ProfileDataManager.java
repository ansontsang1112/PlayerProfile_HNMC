package com.mc.hypernite.playerprofile.manager;

import com.mc.hypernite.playerprofile.database.DatabaseName;
import com.mc.hypernite.playerprofile.interfaces.SQLControlManagerInterface;
import com.mc.hypernite.playerprofile.main.PlayerProfile;
import com.mc.hypernite.playerprofile.utils.DatabaseUtils;
import com.mc.hypernite.playerprofile.utils.Utils;
import jdk.jshell.execution.Util;
import org.bukkit.ChatColor;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class ProfileDataManager implements SQLControlManagerInterface {
    private Connection connection;

    public ProfileDataManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean insert(DatabaseName databaseName, HashMap<String, Object> data) {
        String syntax;
        switch(databaseName) {
            case GENERAL_PROFILE:
                syntax =  "INSERT INTO " + databaseName.label + " VALUES ('" + data.get("uuid") + "', 'false', '" + data.get("account_state") + "" +
                        "', '" + data.get("first_join").toString() + "'," +
                        "'" + data.get("discord_id") + "', 'N/A', 'N/A')";
                break;

            case SW_DATA:
                syntax = "INSERT INTO " + databaseName.label;
                break;

            case COMMAND_RECORD:
                syntax = "INSERT INTO " + databaseName.label;
                break;

            case DEATH_RECORD:
                syntax = "INSERT INTO " + databaseName.label;
                break;

            case MESSAGE_RECORD:
                syntax = "INSERT INTO " + databaseName.label;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + databaseName);
        }

        try (Statement statement = connection.createStatement()) {
            statement.execute(syntax);
            return true;
        } catch (Exception e) {
            PlayerProfile.getPlugin(PlayerProfile.class).getLogger().info(Utils.prefix + " | " + ChatColor.RED + "Fail to insert data to " + databaseName.label);
            return false;
        }
    }

    @Override
    public boolean update(DatabaseName databaseName, Object identificationKey, Object identificationValue, Object recordKey, Object originalData, Object updateData) {
        return false;
    }

    @Override
    public boolean delete(DatabaseName databaseName, Object identificationKey) {
        return false;
    }
}
