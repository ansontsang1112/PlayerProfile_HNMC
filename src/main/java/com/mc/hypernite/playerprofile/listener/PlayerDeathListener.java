package com.mc.hypernite.playerprofile.listener;

import com.mc.hypernite.playerprofile.manager.ConfigManager;
import com.mc.hypernite.playerprofile.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        HashMap<String, Object> hashMap = new HashMap<>();
        String recordID = Utils.UUIDModifier(UUID.randomUUID());

        Player p = e.getEntity().getPlayer();

        class IsNullObject {
            String isObjectNull(Object obj) {
                if(obj == null) {
                    return "Unknown";
                } else {
                    return obj.toString();
                }
            }
        }

        //Prepared Data
        hashMap.put("rid", recordID);
        hashMap.put("uuid", new IsNullObject().isObjectNull(p.getUniqueId()));
        hashMap.put("reason", e.getDeathMessage());
        hashMap.put("timestamp", new Date().getTime());
        hashMap.put("world", p.getWorld().getName());
        hashMap.put("location", p.getLocation().getX() + " " + p.getLocation().getY() + " " + p.getLocation().getZ());
        hashMap.put("server", ConfigManager.serverName);

        //Adopt the data to Global Map
        Utils.deathList.add(hashMap);
        //Utils.errorLogger(hashMap);
    }
}
