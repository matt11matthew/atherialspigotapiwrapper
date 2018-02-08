package me.matthewe.atherial.api.modules.buycraft.events;

import me.matthewe.atherial.api.AtherialEvent;
import me.matthewe.atherial.api.modules.buycraft.BuyCraftApi;
import me.matthewe.atherial.api.modules.buycraft.ban.Ban;

/**
 * Created by Matthew E on 2/7/2018.
 */
public class BuyCraftBanEvent extends AtherialEvent {
    private final Ban ban;
    private final BuyCraftApi buyCraftApi;

    public BuyCraftBanEvent( Ban ban, BuyCraftApi buyCraftApi) {
        super(false);
        this.ban = ban;
        this.buyCraftApi = buyCraftApi;
    }

    public BuyCraftApi getBuyCraftApi() {
        return buyCraftApi;
    }

    public Ban getBan() {
        return ban;
    }
}
