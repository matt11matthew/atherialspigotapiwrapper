package com.gmail.stefvanschiedev.customblocks.events.custom;

import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

import com.gmail.stefvanschiedev.customblocks.block.CustomBlock;

public class CustomBlockPlaceEvent extends CustomBlockEvent implements Cancellable {

	private static final HandlerList HANDLERS = new HandlerList();
	
	private boolean cancelled = false;
	
	public CustomBlockPlaceEvent(CustomBlock cb) {
		super(cb);
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}
	
	public static HandlerList getHandlerList() {
		return HANDLERS;
	}
}