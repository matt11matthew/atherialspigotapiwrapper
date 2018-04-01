package me.matthewe.atherial.api.inventory;

import me.matthewe.atherial.api.inventory.item.ClickHandler;
import me.matthewe.atherial.api.inventory.item.MeInventoryItem;
import me.matthewe.atherial.api.inventory.item.OnClose;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Matthew E on 12/23/2017.
 */
public class MeInventory {
    private String title;
    private int size;
    private OnClose onClose;
    private OnInventoryClick onInventoryClick;
    private Map<Integer, MeInventoryItem> meInventoryItemMap;
    private boolean lockUnknown = true;
    private boolean lockPlayerInventoryClick = true;

    public MeInventory(String title, int size) {
        this.title = ChatColor.translateAlternateColorCodes('8', title);
        this.size = size;
        this.meInventoryItemMap = new ConcurrentHashMap<>();
    }

    public void onPlayerInventoryClick(OnInventoryClick onInventoryClick) {
        this.onInventoryClick = onInventoryClick;
    }

    public boolean isLockPlayerInventoryClick() {
        return lockPlayerInventoryClick;
    }

    public boolean isLockUnknown() {
        return lockUnknown;
    }

    public void setLockPlayerInventoryClick(boolean lockPlayerInventoryClick) {
        this.lockPlayerInventoryClick = lockPlayerInventoryClick;
    }

    public void setLockUnknown(boolean lockUnknown) {
        this.lockUnknown = lockUnknown;
    }

    public void addItem(MeInventoryItem meInventoryItem) {
        int slot;
        if (meInventoryItemMap.isEmpty()) {
            slot = 0;
        } else {
            slot = meInventoryItemMap.keySet().size();
        }
        this.meInventoryItemMap.put(slot, meInventoryItem);
    }

    public void onClose(OnClose onClose) {
        this.onClose = onClose;
    }

    public void onClose(Player player) {
        if (this.onClose != null) {
            this.onClose.onClose(player);
        }
    }

    public void setItem(int slot, MeInventoryItem meInventoryItem) {
        if (this.meInventoryItemMap.containsKey(slot)) {
            this.meInventoryItemMap.remove(slot);
        }
        this.meInventoryItemMap.put(slot, meInventoryItem);
    }

    /**
     * Getter for property 'onClose'.
     *
     * @return Value for property 'onClose'.
     */
    public OnClose getOnClose() {
        return onClose;
    }

    public void open(Player player) {
        player.openInventory(build());
        MeInventoryManager.getInstance().onOpen(player, this);
    }


    /**
     * Getter for property 'title'.
     *
     * @return Value for property 'title'.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter for property 'size'.
     *
     * @return Value for property 'size'.
     */
    public int getSize() {
        return size;
    }

    /**
     * Getter for property 'meInventoryItemMap'.
     *
     * @return Value for property 'meInventoryItemMap'.
     */
    public Map<Integer, MeInventoryItem> getMeInventoryItemMap() {
        return meInventoryItemMap;
    }

    public MeInventory onClick(InventoryClickEvent event) {
        if (event.getInventory().getType() != InventoryType.PLAYER && (event.getInventory().getTitle().equals(ChatColor.translateAlternateColorCodes('&', this.title)))) {
            if (onInventoryClick != null && (event.getClickedInventory().getType() == InventoryType.PLAYER) && (event.getCurrentItem()!=null)&&(event.getCurrentItem().getType()!=Material.AIR)) {
                boolean b = onInventoryClick.onClick((Player) event.getWhoClicked(), event.getCurrentItem(), event.getSlot());
                if (!b) {
                    event.setCancelled(true);
                }
                return this;
            }
            if (this.meInventoryItemMap.containsKey(event.getSlot())) {
                MeInventoryItem meInventoryItem = meInventoryItemMap.get(event.getSlot());
                List<ClickHandler> clickEvents = meInventoryItem.getClickEvents();
                if (!clickEvents.isEmpty()) {
                    clickEvents.forEach(clickHandler -> {
                        clickHandler.run(event);
                        if (event.getClickedInventory().getType() == InventoryType.PLAYER) {
                            if (!lockPlayerInventoryClick) {
                                event.setCancelled(false);
                            }
                        }
                    });
                } else {
                    if (event.getClickedInventory().getType() == InventoryType.PLAYER) {
                        if (!lockPlayerInventoryClick) {
                            return this;
                        }
                    }
                    event.setCancelled(true);
                }
            } else {
                if (lockUnknown) {
                    if (event.getClickedInventory().getType() == InventoryType.PLAYER) {
                        if (!lockPlayerInventoryClick) {
                            return this;
                        }
                    }
                    event.setCancelled(true);
                }
            }
        }
        return this;
    }

    public Inventory build() {
        Inventory inventory = Bukkit.createInventory(null, this.size, this.title);
        this.meInventoryItemMap.forEach((integer, meInventoryItem) -> inventory.setItem(integer, meInventoryItem.toItemStack()));
        return inventory;
    }

    public void addBorder(int row, DyeColor dyeColor) {
        MeInventoryItem meInventoryItem = new MeInventoryItem(Material.STAINED_GLASS_PANE)
                .amount(1)
                .data(dyeColor.getWoolData())
                .named(" ")
                .withClickHandler(new ClickHandler((player, itemStack, slot) -> {

                }, ClickType.values()).lock());
        for (int i = (row * 9) - 9; i < (row * 9); i++) {
            setItem(i, meInventoryItem);
        }
    }

    public void clearSpacing() {
        Map<Integer, MeInventoryItem> inventoryItemMap = new HashMap<>();
        int slot = 0;

        List<Integer> toRemoveList = new ArrayList<>();
        List<Integer> keySet = new ArrayList<>(meInventoryItemMap.keySet());
        keySet.sort((o1, o2) -> o2 - o1);
        for (Integer integer : keySet) {
            MeInventoryItem meInventoryItem = this.meInventoryItemMap.get(integer);
            toRemoveList.add(integer);
            inventoryItemMap.put(slot, meInventoryItem);
            slot++;
        }
        for (Integer integer : toRemoveList) {
            this.meInventoryItemMap.remove(integer);
        }
        inventoryItemMap.forEach((integer, meInventoryItem) -> {
            this.meInventoryItemMap.put(integer, meInventoryItem);
        });
    }

    public void stackItems() {
        Map<Integer, MeInventoryItem> inventoryItemMap = new HashMap<>();
        Map<Integer, MeInventoryItem> stackedInventoryItemMap = new HashMap<>();
        Map<Integer, Integer> slotAmountMap = new HashMap<>();
        Map<ItemStack, MeInventoryItem> itemStackMeInventoryItemHashMap = new HashMap<>();
        List<ItemStack> checkedList = new ArrayList<>();
        int slot = -1;
        for (Integer integer : this.meInventoryItemMap.keySet()) {
            if (slot == -1) {
                slot = integer;
            }
            MeInventoryItem inventoryItem = this.meInventoryItemMap.get(integer);
            if (checkedList.contains(inventoryItem.toItemStack())) {
                slotAmountMap.put(integer, inventoryItem.toItemStack().getAmount());
                inventoryItemMap.put(integer, inventoryItem);
            } else {
                checkedList.add(inventoryItem.toItemStack());
            }
        }
        Map<ItemStack, Integer> amountMap = new HashMap<>();
        for (Integer integer : inventoryItemMap.keySet()) {
            Integer integer1 = slotAmountMap.get(integer);
            MeInventoryItem inventoryItem = inventoryItemMap.get(integer);
            ItemStack itemStack = inventoryItem.toItemStack();
            itemStackMeInventoryItemHashMap.put(itemStack, inventoryItem);
            if (amountMap.containsKey(itemStack)) {
                Integer integer2 = amountMap.get(itemStack);
                amountMap.remove(itemStack);
                amountMap.put(itemStack, (integer2 + integer1));
            } else {
                amountMap.put(itemStack, integer1);
            }
        }

        for (ItemStack itemStack : amountMap.keySet()) {
            Integer integer = amountMap.get(itemStack);
            MeInventoryItem meInventoryItem = itemStackMeInventoryItemHashMap.get(itemStack);
            meInventoryItem.amount(integer);
            stackedInventoryItemMap.put(slot, meInventoryItem);
            slot++;
        }
        List<Integer> toRemoveList = new ArrayList<>();
        for (Integer integer : inventoryItemMap.keySet()) {
            toRemoveList.add(integer);
        }
        for (Integer integer : toRemoveList) {
            meInventoryItemMap.remove(integer);
        }
        stackedInventoryItemMap.forEach((integer, meInventoryItem) -> {
            meInventoryItemMap.put(integer, meInventoryItem);
        });
    }
}