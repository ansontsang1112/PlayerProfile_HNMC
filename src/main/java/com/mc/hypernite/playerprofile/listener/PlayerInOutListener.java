package com.mc.hypernite.playerprofile.listener;

import com.mc.hypernite.playerprofile.database.DataSourcesManager;
import com.mc.hypernite.playerprofile.database.DatabaseName;
import com.mc.hypernite.playerprofile.interfaces.PlayerIO;
import com.mc.hypernite.playerprofile.main.PlayerProfile;
import com.mc.hypernite.playerprofile.manager.ConfigManager;
import com.mc.hypernite.playerprofile.manager.ProfileDataManager;
import com.mc.hypernite.playerprofile.utils.DatabaseUtils;
import com.mc.hypernite.playerprofile.utils.ProfileController;
import com.mc.hypernite.playerprofile.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class PlayerInOutListener extends PlayerIO {
    private ProfileDataManager profileDataManager = new ProfileDataManager(DataSourcesManager.getConnection());

    @Override @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        ProfileController profileController = new ProfileController(e.getPlayer());
        UUID rid = UUID.randomUUID();
        HashMap<String, Object> mappedData = profileController.getMappedProfile();
        mappedData.put("rid", Utils.UUIDModifier(rid));
        mappedData.put("login_time", new Date().getTime());
        mappedData.put("server", ConfigManager.serverName);

        profileDataManager.insert(DatabaseName.SW_DATA, mappedData);
    }

    @Override @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        ProfileController profileController = new ProfileController(e.getPlayer());
        long currentTime = new Date().getTime();

        if(DatabaseUtils.getRIDForNFC(e.getPlayer().getUniqueId(), DataSourcesManager.getConnection()) != "Not Fonud") {
            String key = DatabaseUtils.getRIDForNFC(e.getPlayer().getUniqueId(), DataSourcesManager.getConnection());

            profileDataManager.update(DatabaseName.SW_DATA, "logout_time", currentTime, "rid", key);
            profileDataManager.update(DatabaseName.SW_DATA, "last_loc", profileController.getCurrentXYZ(), "rid", key);
            profileDataManager.update(DatabaseName.SW_DATA, "last_world", profileController.getCurrentLocation().getWorld().getName(), "rid", key);
            profileDataManager.update(DatabaseName.SW_DATA, "health", profileController.getHealth(), "rid", key);
            profileDataManager.update(DatabaseName.SW_DATA, "xp", profileController.getXP(), "rid", key);
        } else {
            Utils.errorLogger("Cannot Sync Logout Message to Database!");
        }
    }
}
