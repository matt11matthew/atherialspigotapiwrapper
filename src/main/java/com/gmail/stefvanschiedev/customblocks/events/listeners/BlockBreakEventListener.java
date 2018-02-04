package com.gmail.stefvanschiedev.customblocks.events.listeners;

import com.gmail.stefvanschiedev.customblocks.block.CustomBlock;
import com.gmail.stefvanschiedev.customblocks.events.custom.CustomBlockBreakEvent;
import com.gmail.stefvanschiedev.customblocks.manager.CustomBlockManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class BlockBreakEventListener implements Listener {

	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockBreak(BlockBreakEvent e) {
		for (int i = 0; i < CustomBlockManager.getInstance().getCustomBlocks().size(); i++) {
			CustomBlock cb = CustomBlockManager.getInstance().getCustomBlocks().get(i);
			if (cb.getLocation().getBlockX() == e.getBlock().getX() &&
					cb.getLocation().getBlockY() == e.getBlock().getY() &&
					cb.getLocation().getBlockZ() == e.getBlock().getZ()) {
				//custom block found
				CustomBlockBreakEvent event = new CustomBlockBreakEvent(cb, e.getPlayer());
				
				Bukkit.getPluginManager().callEvent(event);
				
				e.setExpToDrop(event.getExpToDrop());
				e.setCancelled(event.isCancelled());
				
				if (!event.isCancelled()) {
					cb.destroy();
					i--;
				}
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		if (e.getDamager() == null && !(e.getDamager() instanceof Player))
			return;
		
		for (int i = 0; i < CustomBlockManager.getInstance().getCustomBlocks().size(); i++) {
			CustomBlock cb = CustomBlockManager.getInstance().getCustomBlocks().get(i);
			for (ArmorStand armorStand : cb.getArmorStands()) {
				if (armorStand == e.getEntity()) {
					//custom block found
					CustomBlockBreakEvent event = new CustomBlockBreakEvent(cb, (Player) e.getDamager());
					
					Bukkit.getPluginManager().callEvent(event);
					
					e.setCancelled(event.isCancelled());
					
					if (!event.isCancelled()) {
						cb.destroy();
						i--;
					}
				}
			}
		}
	}
}