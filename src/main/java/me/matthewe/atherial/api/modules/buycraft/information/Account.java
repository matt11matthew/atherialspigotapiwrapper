package me.matthewe.atherial.api.modules.buycraft.information;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Matthew E on 2/4/2018.
 */
public class Account {
    private int id;
    private String domain;
    private String name;
    private Currency currency;
    @JsonProperty("online_mode")
    private boolean onlineMode;
    @JsonProperty("game_type")
    private String gameType;

    public int getId() {
        return id;
    }

    public String getDomain() {
        return domain;
    }

    public String getGameType() {
        return gameType;
    }

    public String getName() {
        return name;
    }

    public Currency getCurrency() {
        return currency;
    }

    public boolean isOnlineMode() {
        return onlineMode;
    }
}
