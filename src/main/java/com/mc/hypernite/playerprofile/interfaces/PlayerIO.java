package com.mc.hypernite.playerprofile.interfaces;

import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public interface PlayerIO {
    public void onPlayerJoin(PlayerJoinEvent e);
    public void onPlayerLeave(PlayerQuitEvent e);
}