package me.matthewe.atherial.api.modules.buycraft.giftcard;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Matthew E on 4/18/2018.
 */
public class GiftCardData {
    @JsonProperty("data")
    private GiftCard giftCard;

    public GiftCard getGiftCard() {
        return giftCard;
    }
}
