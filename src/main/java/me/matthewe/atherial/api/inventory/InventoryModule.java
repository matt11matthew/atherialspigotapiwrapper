package me.matthewe.atherial.api.inventory;

import me.matthewe.atherial.api.module.ApiModule;
import org.bukkit.plugin.Plugin;

/**
 * Created by Matthew E on 2/9/2018.
 */
public class InventoryModule implements ApiModule {
    private Plugin plugin;

    public InventoryModule(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void enable() {
        this.plugin.getServer().getPluginManager().registerEvents(new MeInventoryClickListener(), this.plugin);
        this.plugin.getServer().getPluginManager().registerEvents(new MeInventoryCloseListener(), this.plugin);
    }

    public MeInventory createInventory(String title, int size) {
        return new MeInventory(title, size);
    }

    @Override
    public String getName() {
        return "InventoryModule";
    }
}
