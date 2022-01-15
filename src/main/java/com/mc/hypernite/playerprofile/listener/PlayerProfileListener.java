package com.mc.hypernite.playerprofile.listener;

import com.mc.hypernite.playerprofile.database.DataSourcesManager;
import com.mc.hypernite.playerprofile.database.DatabaseName;
import com.mc.hypernite.playerprofile.interfaces.PlayerIO;
import com.mc.hypernite.playerprofile.main.PlayerProfile;
import com.mc.hypernite.playerprofile.manager.ProfileDataManager;
import com.mc.hypernite.playerprofile.utils.DatabaseUtils;
import com.mc.hypernite.playerprofile.utils.ProfileController;
import com.mc.hypernite.playerprofile.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

public class PlayerProfileListener extends PlayerIO implements Listener {

    @Override @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        ProfileController profileController = new ProfileController(e.getPlayer());

        if(!DatabaseUtils.isPlayerInTable(DatabaseName.GENERAL_PROFILE, profileController.getUUID(), DataSourcesManager.getConnection())) {
            ProfileDataManager profileDataManager = new ProfileDataManager(DataSourcesManager.getConnection());
            HashMap<String, Object> mappedData = profileController.getMappedProfile();
            mappedData.put("account_state", "active");
            mappedData.put("first_join", new Date().getTime());
            mappedData.put("discord_id", "N/A");
            profileDataManager.insert(DatabaseName.GENERAL_PROFILE, mappedData);
            PlayerProfile.getPlugin(PlayerProfile.class).getLogger().info(Utils.prefix + " | " + ChatColor.GREEN + e.getPlayer().getName() + "'s profile was created.");
        }

        PlayerProfile.getPlugin(PlayerProfile.class).getLogger().info(Utils.prefix + " | " + ChatColor.GREEN + e.getPlayer().getName() + " Joined the game.");
    }



    @Override @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {

    }
}
