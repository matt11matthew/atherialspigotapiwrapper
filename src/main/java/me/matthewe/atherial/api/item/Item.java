package me.matthewe.atherial.api.item;

import me.matthewe.atherial.api.Buildable;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Matthew E on 2/4/2018.
 */
public interface Item extends Buildable<ItemStack> {
    Item type(Material material);
    Item durability(int durability);
    Item displayName(String displayName);
    Item lore(String... lore);
    Item amount(int amount);

}
