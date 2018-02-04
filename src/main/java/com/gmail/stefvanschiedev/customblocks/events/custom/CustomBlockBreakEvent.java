package com.gmail.stefvanschiedev.customblocks.events.custom;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

import com.gmail.stefvanschiedev.customblocks.block.CustomBlock;

public class CustomBlockBreakEvent extends CustomBlockEvent implements Cancellable {

	private static final HandlerList HANDLERS = new HandlerList();
	
	private Player player;
	private int expToDrop = 0;
	private boolean cancelled = false;
	
	public CustomBlockBreakEvent(CustomBlock cb, Player player) {
		super(cb);
		this.player = player;
	}
	
	public int getExpToDrop() {
		return expToDrop;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setExpToDrop(int expToDrop) {
		this.expToDrop = expToDrop;
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