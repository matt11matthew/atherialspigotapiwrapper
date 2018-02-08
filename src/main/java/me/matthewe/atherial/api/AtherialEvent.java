package me.matthewe.atherial.api;


import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Matthew E on 2/6/2018.
 */
public abstract class AtherialEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    public AtherialEvent(boolean isAsync) {
        super(isAsync);
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}

