package me.matthewe.atherial.api.modules.buycraft.giftcard;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Matthew E on 4/18/2018.
 */
public class GiftCard {
    private int id;
    private String code;
    private GiftCardBalance giftCardBalance;
    private String note;
    @JsonProperty("void")
    private boolean _void;

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public GiftCardBalance getGiftCardBalance() {
        return giftCardBalance;
    }

    public String getNote() {
        return note;
    }

    public boolean is_void() {
        return _void;
    }
}
