package me.matthewe.atherial.api.item;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

/**
 * Created by Matthew E on 2/4/2018.
 */
public class AtherialSkullItem extends SkullItem {

    @Override
    public SkullItem skullType(SkullType skullType) {
        return (SkullItem) durability(skullType.ordinal());
    }

    @Deprecated
    @Override
    public SkullItem owner(String playerName) {
        if (this.itemStack != null) {
            SkullMeta skullMeta = (SkullMeta) this.itemStack.getItemMeta();
            skullMeta.setOwner(playerName);
            this.itemStack.setItemMeta(skullMeta);
        }
        return this;
    }

    @Override
    public SkullItem owner(UUID uuid) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);
        if (offlinePlayer != null) {
            return owner(offlinePlayer);
        }
        return this;
    }

    @Override
    public SkullItem owner(OfflinePlayer offlinePlayer) {
        if (this.itemStack != null) {
            SkullMeta skullMeta = (SkullMeta) this.itemStack.getItemMeta();
            skullMeta.setOwner(offlinePlayer.getName());
            this.itemStack.setItemMeta(skullMeta);
        }
        return this;
    }

    @Override
    public SkullItem owner(Player player) {
        return owner((OfflinePlayer) player);
    }
}
