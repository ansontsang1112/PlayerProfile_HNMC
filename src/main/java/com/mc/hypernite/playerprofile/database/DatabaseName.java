package com.mc.hypernite.playerprofile.database;

class TableName {
    static String generalProfileSQL = "CREATE TABLE IF NOT EXISTS player_general_profile (uuid varchar(50), player_enable varchar(10), account_state varchar(50), profile_gen_time long," +
            "discord_id varchar(50), email varchar(100), description varchar(5000))";
    static String swDataSQL = "CREATE TABLE IF NOT EXISTS player_sw_data (rid varchar(150), uuid varchar(50),last_loc varchar(100), last_world varchar(50), login_ip varchar(30)," +
            "login_time long, logout_time long, health int(11), xp int(11), server varchar(30))";
    static String commandRecordSQL = "CREATE TABLE IF NOT EXISTS player_command_record (rid varchar(150), uuid varchar(50),command varchar(1000), timestamp long, world varchar(30)," +
            "server varchar(30))";
    static String deathRecordSQL = "CREATE TABLE IF NOT EXISTS player_death_record (rid varchar(150), uuid varchar(50),attacker varchar(20), reason varchar(5000)," +
            "timestamp long, world varchar(30), location varchar(30), server varchar(30))";
    static String messageRecordSQL = "CREATE TABLE IF NOT EXISTS player_message_record (rid varchar(150), uuid varchar(50),message varchar(5000), timestamp long, world varchar(30)," +
            "location varchar(30), server varchar(30))";
}

public enum DatabaseName {
    GENERAL_PROFILE("player_general_profile", TableName.generalProfileSQL),
    SW_DATA("player_sw_data", TableName.swDataSQL),
    COMMAND_RECORD("player_command_record", TableName.commandRecordSQL),
    DEATH_RECORD("player_death_record", TableName.deathRecordSQL),
    MESSAGE_RECORD("player_message_record", TableName.messageRecordSQL);

    public final String label, sql;

    DatabaseName(String label, String sql) {
        this.label = label;
        this.sql = sql;
    }
}
