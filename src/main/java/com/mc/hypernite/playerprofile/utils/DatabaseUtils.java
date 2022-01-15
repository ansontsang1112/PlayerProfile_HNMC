package com.mc.hypernite.playerprofile.utils;

import com.mc.hypernite.playerprofile.database.DatabaseName;
import com.mc.hypernite.playerprofile.main.PlayerProfile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class DatabaseUtils {

    public static boolean isPlayerInTable(DatabaseName tableName, UUID playerUUID, Connection connection) {

        String sql = "SELECT * FROM " + tableName.label + " WHERE uuid = '" + playerUUID.toString() + "'";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            return resultSet.next();
        } catch (SQLException e) {
            //System.out.println(e.getMessage()); #For Test Only (Result Set)
            return false;
        }
    }

    public static String getRIDForNFC(UUID playerUUID, Connection connection) {
        String sql = "SELECT rid FROM " + DatabaseName.SW_DATA.label + "' WHERE uuid = '" + playerUUID + "' AND logout_time = 'N/A'";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if(!resultSet.next()) throw new SQLException();
            return resultSet.getString("rid");
        } catch (SQLException e) {
            //System.out.println(e.getMessage()); #For Test Only (Result Set)
            return "Not Found";
        }
    }
}
