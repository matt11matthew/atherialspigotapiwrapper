package me.matthewe.atherial.api.modules.buycraft.events;

import me.matthewe.atherial.api.AtherialEvent;
import me.matthewe.atherial.api.modules.buycraft.BuyCraftApi;
import me.matthewe.atherial.api.modules.buycraft.payments.Payment;

/**
 * Created by Matthew E on 2/6/2018.
 */
public class BuyCraftPaymentEvent extends AtherialEvent {
    private final Payment payment;
    private final BuyCraftApi buyCraftApi;

    public BuyCraftPaymentEvent(Payment payment, BuyCraftApi buyCraftApi) {
        super(false);
        this.payment = payment;
        this.buyCraftApi = buyCraftApi;
    }

    public BuyCraftApi getBuyCraftApi() {
        return buyCraftApi;
    }

    public Payment getPayment() {
        return payment;
    }
}
