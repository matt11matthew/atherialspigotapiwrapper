package me.matthewe.atherial.api.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

import java.util.Map;
import java.util.UUID;

/**
 * Created by Matthew E on 12/23/2017.
 */
public class MeInventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getSlotType() != InventoryType.SlotType.OUTSIDE && (event.getWhoClicked() instanceof Player)) {
            Player player = (Player) event.getWhoClicked();
            Map<UUID, MeInventory> playerMeInventoryMap = MeInventoryManager.getInstance().getPlayerMeInventoryMap();
            if (playerMeInventoryMap.containsKey(player.getUniqueId())) {
                MeInventory meInventory = playerMeInventoryMap.get(player.getUniqueId());
                meInventory.onClick(event);
            }
        }
    }
}
