package me.matthewe.atherial.api.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

/**
 * Created by Matthew E on 12/24/2017.
 */
public class MeInventoryCloseListener implements Listener {
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getPlayer() instanceof Player) {
            Player player = (Player) event.getPlayer();
            if (MeInventoryManager.getInstance().getPlayerMeInventoryMap().containsKey(player.getUniqueId())) {
                MeInventoryManager.getInstance().onClose(player);
            }
        }
    }
}
