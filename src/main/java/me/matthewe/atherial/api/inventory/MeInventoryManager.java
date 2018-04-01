package me.matthewe.atherial.api.inventory;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Matthew E on 12/23/2017.
 */
public class MeInventoryManager {
    private static MeInventoryManager instance;
    private Map<UUID, MeInventory> playerMeInventoryMap;


    public static MeInventoryManager getInstance() {
        if (instance == null) {
            instance = new MeInventoryManager();
        }
        return instance;
    }

    public MeInventoryManager() {
        instance = this;
        this.playerMeInventoryMap = new HashMap<>();
    }

    /**
     * Getter for property 'playerMeInventoryMap'.
     *
     * @return Value for property 'playerMeInventoryMap'.
     */
    public Map<UUID, MeInventory> getPlayerMeInventoryMap() {
        return playerMeInventoryMap;
    }

    public MeInventory getMeInventory(UUID uuid) {
        if (this.playerMeInventoryMap.containsKey(uuid)) {
            return playerMeInventoryMap.get(uuid);
        }
        return null;
    }

    public void onOpen(Player player, MeInventory meInventory) {
        if (this.playerMeInventoryMap.containsKey(player.getUniqueId())) {
            this.playerMeInventoryMap.remove(player.getUniqueId());
        }
        this.playerMeInventoryMap.put(player.getUniqueId(), meInventory);
    }

    public void onClose(Player player) {
        if (this.playerMeInventoryMap.containsKey(player.getUniqueId())) {
            MeInventory meInventory = this.playerMeInventoryMap.get(player.getUniqueId());
            meInventory.onClose(player);
            this.playerMeInventoryMap.remove(player.getUniqueId());
        }
    }
}
