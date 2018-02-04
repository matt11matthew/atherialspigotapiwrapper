package com.gmail.stefvanschiedev.customblocks.manager;

import com.gmail.stefvanschiedev.customblocks.block.CustomBlock;

import java.util.ArrayList;
import java.util.List;

public class CustomBlockManager {
	
	private CustomBlockManager() {}
	private static CustomBlockManager instance = new CustomBlockManager();
	public static CustomBlockManager getInstance() {
		return instance;
	}
	
	private List<CustomBlock> customBlocks = new ArrayList<>();
	
	public List<CustomBlock> getCustomBlocks() {
		return customBlocks;
	}
	
	public void addCustomBlock(CustomBlock cb) {
		customBlocks.add(cb);
	}
	
	public void removeCustomBlock(CustomBlock cb) {
		customBlocks.remove(cb);
	}
}