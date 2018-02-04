package me.matthewe.atherial.api.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

/**
 * Created by Matthew E on 2/4/2018.
 */
public class AtherialItem implements Item {
    protected ItemStack itemStack;

    @Override
    public Item type(Material material) {
        if (this.itemStack == null) {
            this.itemStack = new ItemStack(material);
        }
        this.itemStack.setType(material);
        return this;
    }

    @Override
    public Item durability(int durability) {
        if (this.itemStack != null) {
            this.itemStack.setDurability((short) durability);
        }
        return this;
    }

    @Override
    public Item displayName(String displayName) {
        if (this.itemStack != null) {
            ItemMeta itemMeta = this.itemStack.getItemMeta();
            itemMeta.setDisplayName(displayName);
            this.itemStack.setItemMeta(itemMeta);
        }
        return this;
    }

    @Override
    public Item lore(String... lore) {
        if (this.itemStack != null) {
            ItemMeta itemMeta = this.itemStack.getItemMeta();
            itemMeta.setLore(Arrays.asList(lore));
            this.itemStack.setItemMeta(itemMeta);
        }
        return this;
    }

    @Override
    public Item amount(int amount) {
        if (this.itemStack != null) {
            this.itemStack.setAmount(amount);
        }
        return this;
    }

    @Override
    public ItemStack build() {
        return itemStack;
    }
}
