package me.matthewe.atherial.api.modules;

import com.wasteofplastic.askyblock.*;
import me.matthewe.atherial.api.module.ApiWrapperModule;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by Matthew E on 2/7/2018.
 */
public class ASkyBlockApiWrapperModule implements ApiWrapperModule {

    @Override
    public void enable() {

    }

    public Players getPlayer(UUID uuid) {
        PlayerCache players = ASkyBlock.getPlugin().getPlayers();
        return players.get(uuid);
    }

    public Island getIslandAt(Location location) {
        return ASkyBlockAPI.getInstance().getIslandAt(location);
    }

    public Island getIslandOwnedBy(UUID owner) {
        return ASkyBlockAPI.getInstance().getIslandOwnedBy(owner);
    }

    public boolean hasIsland(UUID owner) {
        return ASkyBlockAPI.getInstance().hasIsland(owner);
    }

    public boolean isPlayerOnIsland(Player player) {
        return ASkyBlockAPI.getInstance().playerIsOnIsland(player);
    }


    public int getIslandCount(Player player) {
        return ASkyBlockAPI.getInstance().getIslandCount();
    }


    @Override
    public String getPluginName() {
        return "ASkyBlock";
    }

    @Override
    public String getName() {
        return "ASkyBlockApiWrapperModule";
    }
}
