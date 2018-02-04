package me.matthewe.atherial.api.item;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by Matthew E on 2/4/2018.
 */
public abstract class SkullItem extends AtherialItem {
    abstract SkullItem skullType(SkullType skullType);

    @Deprecated
    abstract SkullItem owner(String playerName);

    abstract SkullItem owner(UUID uuid);

    abstract SkullItem owner(OfflinePlayer offlinePlayer);

    abstract SkullItem owner(Player player);
}