package com.gmail.stefvanschiedev.customblocks.events.custom;

import org.bukkit.event.Event;

import com.gmail.stefvanschiedev.customblocks.block.CustomBlock;

public abstract class CustomBlockEvent extends Event {

	private CustomBlock cb;
	
	public CustomBlockEvent(CustomBlock cb) {
		this.cb = cb;
	}
	
	public CustomBlock getCustomBlock() {
		return cb;
	}
}