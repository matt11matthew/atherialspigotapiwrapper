package me.matthewe.atherial.api.title;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.util.Collection;

/**
 * Created by Matthew E on 2/4/2018.
 */
public class AtherialTitle implements Title {
    private String title;
    private String subTitle;
    private int stay;
    private int fadeIn;
    private int fadeOut;

    @Override
    public Title title(String text) {
        this.title = text;
        return this;
    }

    @Override
    public Title subTitle(String text) {
        this.subTitle = text;
        return this;
    }

    @Override
    public Title fadeIn(int fadeIn) {
        this.fadeIn = fadeIn;
        return this;
    }

    @Override
    public Title stay(int stay) {
        this.stay = stay;
        return this;
    }

    @Override
    public Title fadeOut(int fadeOut) {
        this.fadeOut = fadeOut;
        return this;
    }

    @Override
    public Title send(Player... players) {
        for (Player player : players) {
            this.sendTitleAndSubTitle(player, (title == null ? "" : title), (subTitle == null ? "" : subTitle), fadeIn, (stay == 0 ? 20 : 0), fadeOut);
        }
        return this;
    }

    @Override
    public Title send(Collection<? extends Player> onlinePlayers) {
        for (Player onlinePlayer : onlinePlayers) {
            this.send(onlinePlayer);
        }
        return this;
    }

    private void sendTitleAndSubTitle(Player player, String text, String subTitle, int fadeInTime, int showTime, int fadeOutTime) {
        try {
            Object chatTitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\": \"" + text + "\"}");

            Constructor<?> titleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), int.class, int.class, int.class);
            Object packet = titleConstructor.newInstance(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null), chatTitle, fadeInTime, showTime, fadeOutTime);

            Object chatSubTitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\": \"" + subTitle + "\"}");

            Constructor<?> subTitleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), int.class, int.class, int.class);
            Object packetSubTitle = subTitleConstructor.newInstance(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null), chatSubTitle, fadeInTime, showTime, fadeOutTime);

            sendPacket(player, packet);
            sendPacket(player, packetSubTitle);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void sendPacket(Player player, Object packet) {
        try {
            Object handle = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Class<?> getNMSClass(String name) {
        try {
            return Class.forName("net.minecraft.server" + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + "." + name);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
