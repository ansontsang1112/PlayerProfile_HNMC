package com.mc.hypernite.playerprofile.manager;

import com.mc.hypernite.playerprofile.database.DatabaseName;
import com.mc.hypernite.playerprofile.interfaces.SQLControlManagerInterface;
import com.mc.hypernite.playerprofile.main.PlayerProfile;
import com.mc.hypernite.playerprofile.utils.Utils;
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
                        "', '" + data.get("first_join") + "'," +
                        "'" + data.get("discord_id") + "', 'N/A', 'N/A')";
                break;

            case SW_DATA:
                syntax = "INSERT INTO " + databaseName.label + " VALUES ('" + data.get("rid") + "', '" + data.get("uuid") + "', '" + data.get("vector") + "" +
                        "', '" + data.get("world") + "'," +
                        "'" + data.get("ip") + "', '" + data.get("login_time") + "', 'N/A', '" + data.get("health") + "', '" + data.get("xp") +"', " +
                        "'" + data.get("server") + "')";
                break;

            case COMMAND_RECORD:
                syntax =  "INSERT INTO " + databaseName.label + " VALUES ('" + data.get("rid") + "', '"+ data.get("uuid") +"', '" + data.get("command") + "" +
                        "', '" + data.get("timestamp") + "'," +
                        "'" + data.get("world") + "', '" + data.get("world") + "')";
                break;

            case DEATH_RECORD:
                syntax =  "INSERT INTO " + databaseName.label + " VALUES ('" + data.get("rid") + "', '"+ data.get("uuid") +"', '" + data.get("attacker") + "" +
                        "', '" + data.get("reason") + "'," +
                        "'" + data.get("timestamp") + "', '" + data.get("world") + "', '" + data.get("vector") + "', '" + data.get("server") + "')";
                break;

            case MESSAGE_RECORD:
                syntax =  "INSERT INTO " + databaseName.label + " VALUES ('" + data.get("rid") + "', '"+ data.get("uuid") +"', '" + data.get("message") + "" +
                        "', '" + data.get("timestamp") + "'," +
                        "'" + data.get("world") + "', '" + data.get("server") + "')";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + databaseName);
        }

        try (Statement statement = connection.createStatement()) {
            statement.execute(syntax);
            return true;
        } catch (Exception e) {
            PlayerProfile.getPlugin(PlayerProfile.class).getLogger().info(Utils.prefix + " | " + ChatColor.RED + "Fail to insert data to " + databaseName.label);
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(DatabaseName databaseName, Object recordKey, Object updateData, Object identificationKey, Object identificationValue) {
        try (Statement statement = connection.createStatement()) {
            String syntax = "UPDATE " + databaseName.label + " SET " + recordKey + " = '" + updateData + "' WHERE " + identificationKey + " = '" + identificationValue + "'";
            statement.execute(syntax);
            return true;
        } catch (Exception e) {
            PlayerProfile.getPlugin(PlayerProfile.class).getLogger().info(Utils.prefix + " | " + ChatColor.RED + "Fail to update data " + recordKey + " in " + databaseName.label);
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(DatabaseName databaseName, Object identificationKey, Object identificationValue) {
        return false;
    }
}
