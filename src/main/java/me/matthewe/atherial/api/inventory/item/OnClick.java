package me.matthewe.atherial.api.inventory.item;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Matthew E on 12/23/2017.
 */
@FunctionalInterface
public interface OnClick {
    void onClick(Player player, ItemStack itemStack, int slot);
}
