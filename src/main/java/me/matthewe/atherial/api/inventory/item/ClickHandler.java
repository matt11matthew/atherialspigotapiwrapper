package me.matthewe.atherial.api.inventory.item;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Matthew E on 12/23/2017.
 */
public class ClickHandler {
    private List<ClickType> clickTypeList;
    private OnClick onClick;
    private boolean cancel;
    private boolean close;

    public ClickHandler(OnClick onClick, ClickType... clickTypes) {
        this.onClick = onClick;
        this.cancel = false;
        this.close = false;
        this.clickTypeList = new ArrayList<>();
        if (clickTypes != null) {
            this.clickTypeList.addAll(Arrays.asList(clickTypes));
        }
    }

    public ClickHandler lock() {
        this.cancel = true;
        return this;
    }

    public void run(InventoryClickEvent event) {
        if (this.cancel) {
            event.setCancelled(true);
        }
        if (clickTypeList.contains(event.getClick())) {
            if (this.cancel) {
                event.setCancelled(true);
            }
            if (this.onClick != null) {
                this.onClick.onClick((Player) event.getWhoClicked(), event.getCurrentItem(), event.getSlot());
                if (close) {
                    event.getWhoClicked().closeInventory();
                }
            }
        } else {
            if (this.cancel) {
                this.setCancelled(true);

            }
        }
    }

    public OnClick getOnClick() {
        return onClick;
    }

    public boolean isCancelled() {
        return cancel;
    }

    public ClickHandler setCancelled(boolean cancel) {
        this.cancel = cancel;
        return this;
    }

    public ClickHandler closeOnClick() {
        this.close = true;
        return this;
    }
}
