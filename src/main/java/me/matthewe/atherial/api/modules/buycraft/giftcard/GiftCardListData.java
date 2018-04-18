package me.matthewe.atherial.api.modules.buycraft.giftcard;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Matthew E on 4/18/2018.
 */
public class GiftCardListData {
    @JsonProperty("data")
    private GiftCard[] giftCards;

    public GiftCard[] getGiftCards() {
        return giftCards;
    }
}
