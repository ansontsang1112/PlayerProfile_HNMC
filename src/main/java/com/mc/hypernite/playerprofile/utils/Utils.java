package com.mc.hypernite.playerprofile.utils;

import java.util.*;

public class Utils {
    public static String prefix = "HNPPS";

    public static TreeMap<Object, HashMap<String, Object>> commandMaps = new TreeMap<>();

    public static String UUIDModifier(UUID uuid) {
        String suuid = uuid.toString();
        String [] splitedUUID = suuid.split("-");
        for(String u : splitedUUID) {
            suuid += u;
        }
        return suuid;
    }
}
