package me.matthewe.atherial.api.inventory.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthew E on 12/23/2017.
 */
public class MeInventoryItem {
    private ItemStack itemStack;
    private List<ClickHandler> clickEvents;

    public MeInventoryItem(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.clickEvents = new ArrayList<>();
    }

    public MeInventoryItem withClickHandler(ClickHandler clickHandler) {
        if (clickHandler == null) {
            return this;
        }
        if (this.clickEvents == null) {
            this.clickEvents = new ArrayList<>();
        }
        this.clickEvents.add(clickHandler);
        return this;
    }

    /**
     * Getter for property 'clickEvents'.
     *
     * @return Value for property 'clickEvents'.
     */
    public List<ClickHandler> getClickEvents() {
        return clickEvents;
    }

    public MeInventoryItem(Material material) {
        this.itemStack = new ItemStack(material);
    }

    public MeInventoryItem amount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    public MeInventoryItem data(int data) {
        this.itemStack.setDurability((short) data);
        return this;
    }

    public ItemStack toItemStack() {
        return itemStack;
    }

    public MeInventoryItem clearItemMeta() {
        itemStack.setItemMeta(new ItemStack(itemStack.getType(), itemStack.getAmount(), itemStack.getDurability()).getItemMeta());
        return this;
    }

    public MeInventoryItem named(String name) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public MeInventoryItem lore(String... lore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> loreStringList =  new ArrayList<>();
        for (String loreLine : lore) {
            if (loreLine == null || (loreLine.equals(""))) {
                continue;
            }
            loreStringList.add(ChatColor.translateAlternateColorCodes('&',  loreLine));
        }
        itemMeta.setLore(loreStringList);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public MeInventoryItem lore(List<String> lore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> loreStringList =  new ArrayList<>();
        for (String loreLine : lore) {
            loreStringList.add(ChatColor.translateAlternateColorCodes('&',  loreLine));
        }
        itemMeta.setLore(loreStringList);
        itemStack.setItemMeta(itemMeta);
        return this;
    }
}
