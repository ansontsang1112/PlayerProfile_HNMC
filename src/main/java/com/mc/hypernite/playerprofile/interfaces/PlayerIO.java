package com.mc.hypernite.playerprofile.interfaces;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public abstract class PlayerIO implements Listener {
    @EventHandler
    public abstract void onPlayerJoin(PlayerJoinEvent e);
    @EventHandler
    public abstract void onPlayerLeave(PlayerQuitEvent e);
}