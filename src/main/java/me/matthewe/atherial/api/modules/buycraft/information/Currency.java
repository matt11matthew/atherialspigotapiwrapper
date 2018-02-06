package me.matthewe.atherial.api.modules.buycraft.information;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Matthew E on 2/4/2018.
 */
public class Currency {
    @JsonProperty("iso_4217")
    private String iso4217;

    private String symbol;


    public String getIso4217() {
        return iso4217;
    }

    public String getSymbol() {
        return symbol;
    }
}
