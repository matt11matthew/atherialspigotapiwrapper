package me.matthewe.atherial.api;

import com.gmail.stefvanschiedev.customblocks.events.listeners.BlockBreakEventListener;
import com.gmail.stefvanschiedev.screenapi.screen.Screen;
import me.matthewe.atherial.api.item.AtherialItem;
import me.matthewe.atherial.api.item.AtherialSkullItem;
import me.matthewe.atherial.api.item.Item;
import me.matthewe.atherial.api.item.SkullItem;
import me.matthewe.atherial.api.title.AtherialTitle;
import me.matthewe.atherial.api.title.Title;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

/**
 * Created by Matthew E on 2/4/2018.
 */
public class SpigotAtherialApi implements AtherialApi {
    private static AtherialApi instance;

    public static AtherialApi getInstance() {
        return instance == null ? instance = new SpigotAtherialApi() : instance;
    }

    @Override
    public void init(Plugin plugin) {
        PluginManager pluginManager = plugin.getServer().getPluginManager();

        pluginManager.registerEvents(new BlockBreakEventListener(), plugin);
    }

    @Override
    public Screen createScreen(Player player) {
        return new Screen(player);
    }

    @Override
    public SkullItem createSkullItem() {
        return new AtherialSkullItem();
    }

    @Override
    public Item createItem() {
        return new AtherialItem();
    }

    @Override
    public Title createTitle() {
        return new AtherialTitle();
    }
}
