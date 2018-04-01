package me.matthewe.atherial.api.modules.buycraft;

import me.matthewe.atherial.api.modules.buycraft.ban.Ban;
import me.matthewe.atherial.api.modules.buycraft.checkout.Checkout;
import me.matthewe.atherial.api.modules.buycraft.commandqueue.CommandQueue;
import me.matthewe.atherial.api.modules.buycraft.information.Information;
import me.matthewe.atherial.api.modules.buycraft.listings.Listings;
import me.matthewe.atherial.api.modules.buycraft.payments.Payment;

import java.util.List;

/**
 * Created by Matthew E on 2/4/2018.
 */
public interface BuyCraftApi {
    String getSecret();

    void updateBans();

    Ban createBan(String user, String ip, String reason);

    Ban createBan(String user, String reason);

    Checkout generateCheckout(String username, int packageId);

    Ban createBan(String user);

    void loadBanCache();

    void saveBanCache();

    void setSecret(String secret);

    Listings.BuyCraftPackage getBuyCraftPackage(int id);


    void callEvents();

    Information getInformation();

    void loadPaymentCache();

    void savePaymentCache();

    me.matthewe.atherial.api.modules.buycraft.ban.BanList getBanList();


    CommandQueue getCommandQueue();

    Listings getListings();

    List<Payment> getCurrentCachedPayments();

    void updateCache();

    void updatePayments();

}
