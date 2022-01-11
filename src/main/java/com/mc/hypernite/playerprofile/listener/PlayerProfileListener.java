package com.mc.hypernite.playerprofile.listener;

import com.mc.hypernite.playerprofile.interfaces.PlayerIO;
import com.mc.hypernite.playerprofile.main.PlayerProfile;
import com.mc.hypernite.playerprofile.utils.ProfileController;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerProfileListener implements PlayerIO, Listener {

    @Override
    public void onPlayerJoin(PlayerJoinEvent e) {
        ProfileController profileController = new ProfileController(e.getPlayer());


    }

    @Override
    public void onPlayerLeave(PlayerQuitEvent e) {

    }
}
