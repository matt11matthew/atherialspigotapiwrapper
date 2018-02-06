package me.matthewe.atherial.api.item;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by Matthew E on 2/4/2018.
 */
public abstract class SkullItem extends AtherialItem {
    public  abstract SkullItem skullType(SkullType skullType);

    @Deprecated
    public  abstract SkullItem owner(String playerName);

    public  abstract SkullItem owner(UUID uuid);

    public   abstract SkullItem owner(OfflinePlayer offlinePlayer);

    public abstract SkullItem owner(Player player);
}