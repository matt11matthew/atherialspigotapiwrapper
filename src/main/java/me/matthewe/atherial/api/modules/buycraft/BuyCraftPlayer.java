package me.matthewe.atherial.api.modules.buycraft;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BuyCraftPlayer {
    private int id;
    private String name;
    private String uuid;

    public Player asBukkitPlayer() {
        return Bukkit.getPlayer(name);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUuid() {
        return uuid;
    }
}