package com.mc.hypernite.playerprofile.database;

class TableName {
    static String generalProfileSQL = "CREATE TABLE 'player_general_profile' ('uuid' varchar(50),'player_enable' varchar(10), 'account_state' varchar(50), 'first_join' varchar(30)," +
            "'discord_id' varchar(50), 'email' varchar(100), 'description' varchar(5000))";
    static String swDataSQL = "CREATE TABLE 'player_sw_data' ('uuid' varchar(50),'last_loc' varchar(30), 'last_world' varchar(50), 'login_ip' varchar(30)," +
            "'login_time' varchar(50), 'logout_time' varchar(50), 'health' int(11), 'xp' int(11), 'server' varchar(30))";
    static String commandRecordSQL = "CREATE TABLE 'player_command_record' ('uuid' varchar(50),'command' varchar(1000), 'timestamp' varchar(50), 'world' varchar(30)," +
            "'server' varchar(30))";
    static String deathRecordSQL = "CREATE TABLE 'Player_death_record' ('uuid' varchar(50),'attacker' varchar(20), 'reason' varchar(5000)," +
            "'timestamp' varchar(50), 'world' varchar(30), 'location' varchar(30), 'server' varchar(30))";
    static String messageRecordSQL = "CREATE TABLE 'player_message_record' ('uuid' varchar(50),'message' varchar(5000), 'timestamp' varchar(30), 'world' varchar(30)," +
            "'location' varchar(30), 'server' varchar(30))";
}

public enum DatabaseName {
    GENERAL_PROFILE("player_general_profile", TableName.generalProfileSQL),
    SW_DATA("player_sw_data", TableName.swDataSQL),
    COMMAND_RECORD("player_command_record", TableName.commandRecordSQL),
    DEATH_RECORD("Player_death_record", TableName.deathRecordSQL),
    MESSAGE_RECORD("player_message_record", TableName.messageRecordSQL);

    public final String label, sql;

    DatabaseName(String label, String sql) {
        this.label = label;
        this.sql = sql;
    }
}
