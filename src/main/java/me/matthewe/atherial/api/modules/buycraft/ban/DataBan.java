package me.matthewe.atherial.api.modules.buycraft.ban;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Matthew E on 2/6/2018.
 */
public class DataBan {
    @JsonProperty("data")
    private Ban ban;

    public Ban getBan() {
        return ban;
    }
}
