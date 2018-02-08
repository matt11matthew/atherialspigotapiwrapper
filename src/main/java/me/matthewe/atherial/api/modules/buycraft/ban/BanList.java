package me.matthewe.atherial.api.modules.buycraft.ban;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bukkit.entity.Player;

/**
 * Created by Matthew E on 2/6/2018.
 */
public class BanList {
    @JsonProperty("data")
    private Ban[] bans;

    public Ban[] getBans() {
        return bans;
    }

    public boolean isBanned(Player player) {
        for (Ban ban : bans) {
            if (ban.getUser().getIgn().equals(player.getName()) && (ban.getUser().getUuid().equals(player.getUniqueId().toString().replaceAll("-", "").trim()))) {
                return true;
            }
        }
        return false;
    }
}
