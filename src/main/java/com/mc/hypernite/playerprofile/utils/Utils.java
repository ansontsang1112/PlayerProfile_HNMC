package com.mc.hypernite.playerprofile.utils;

import com.mc.hypernite.playerprofile.main.PlayerProfile;
import org.bukkit.ChatColor;

import java.util.*;

public class Utils {
    public static String prefix = "HNPPS";

    public static ArrayList<HashMap> commandList = new ArrayList<>();
    public static ArrayList<HashMap> deathList = new ArrayList<>();

    public static String UUIDModifier(UUID uuid) {
        String [] splitedUUID = uuid.toString().split("-");
        String returnUUID = "";
        for(String u : splitedUUID) {
            returnUUID += u;
        }
        return returnUUID;
    }

    public static <T> void errorLogger(T errMsg) {
        PlayerProfile.getPlugin(PlayerProfile.class).getLogger().info(Utils.prefix + " | " + ChatColor.RED + " Error Occurs: " + errMsg.toString());
    }
}
