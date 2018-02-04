package me.matthewe.atherial.api;

import com.gmail.stefvanschiedev.screenapi.screen.Screen;
import me.matthewe.atherial.api.item.Item;
import me.matthewe.atherial.api.item.SkullItem;
import me.matthewe.atherial.api.title.Title;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * Created by Matthew E on 2/4/2018.
 */
public interface AtherialApi {
    void init(Plugin plugin);

    Screen createScreen(Player player);

    SkullItem createSkullItem();

    Item createItem();

    Title createTitle();

}
