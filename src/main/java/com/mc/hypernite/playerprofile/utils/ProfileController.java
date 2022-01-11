package com.mc.hypernite.playerprofile.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.UUID;

public class ProfileController {
    private Player player;

    public ProfileController(Player player) {
        this.player = player;
    }

    public Location getCurrentLocation() {
        return player.getLocation();
    }

    public Location getBedLocation() {
        return (player.getBedSpawnLocation() == null) ? new Location(player.getWorld(), 0,0,0) : player.getBedSpawnLocation();
    }

    public Integer getXP() {
        return player.getLevel();
    }

    public Double getHealth() {
        return player.getHealth();
    }

    public UUID getUUID() {
        return player.getUniqueId();
    }

    public InetSocketAddress getSocketAddress() {
        return player.getAddress();
    }

    public HashMap<String, Object> getMappedProfile() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("uuid", getUUID());
        map.put("world", getCurrentLocation().getWorld());
        map.put("vector", getCurrentLocation().getX() + " " + getCurrentLocation().getY() + " " + getCurrentLocation().getZ());
        map.put("bedSpawn", getBedLocation().toString());
        map.put("xp", getXP());
        map.put("health", getHealth());
        map.put("ip", getSocketAddress().getAddress().toString());

        return map;
    }
}
