package me.matthewe.atherial.api.inventory.item;

import org.bukkit.entity.Player;

/**
 * Created by Matthew E on 12/23/2017.
 */
@FunctionalInterface
public interface OnClose {
    void onClose(Player player);
}
