package com.mc.hypernite.playerprofile.listener;

import com.mc.hypernite.playerprofile.manager.ConfigManager;
import com.mc.hypernite.playerprofile.utils.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class PlayerCommandListener implements Listener {

    @EventHandler
    public void onCommandTyped(PlayerCommandPreprocessEvent e) {

        HashMap<String, Object> hashMap = new HashMap<>();
        String randomID = Utils.UUIDModifier(UUID.randomUUID());

        //Prepared Data
        hashMap.put("rid", randomID);
        hashMap.put("uuid", e.getPlayer().getUniqueId());
        hashMap.put("command", e.getMessage());
        hashMap.put("timestamp", new Date().getTime());
        hashMap.put("world", e.getPlayer().getWorld().getName());
        hashMap.put("server", ConfigManager.serverName);

        //Adopt the data to Global Map
        Utils.commandList.add(hashMap);
    }
}
