package me.matthewe.atherial.api.modules.placeholderapi;

import org.bukkit.entity.Player;

/**
 * Created by Matthew E on 2/6/2018.
 */
@FunctionalInterface
public interface PlaceHolderRequest {
    String onPlaceholderRequest(Player player, String identifier);
}
