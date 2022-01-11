package com.mc.hypernite.playerprofile.interfaces;

import com.mc.hypernite.playerprofile.database.DatabaseName;

import java.util.HashMap;

public interface SQLControlManagerInterface {
    default boolean insert(DatabaseName databaseName, HashMap<String, Object> data) {
        return false;
    }
    default boolean update(DatabaseName databaseName, Object identificationKey, Object identificationValue, Object recordKey, Object originalData, Object updateData) {
        return false;
    }
    default boolean delete(DatabaseName databaseName, Object identificationKey) {
        return false;
    }
}
