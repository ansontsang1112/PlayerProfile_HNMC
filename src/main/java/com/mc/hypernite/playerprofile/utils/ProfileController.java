package com.mc.hypernite.playerprofile.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
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
        return player.getBedSpawnLocation();
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
}
