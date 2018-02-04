package me.matthewe.atherial.api.title;

import org.bukkit.entity.Player;

/**
 * Created by Matthew E on 2/4/2018.
 */
public interface Title {
    Title title(String text);

    Title subTitle(String text);

    Title fadeIn(int fadeIn);

    Title stay(int stay);

    Title fadeOut(int fadeOut);

    Title send(Player... players);
}
