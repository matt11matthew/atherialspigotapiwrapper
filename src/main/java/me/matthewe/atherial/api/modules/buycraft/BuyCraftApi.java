package me.matthewe.atherial.api.modules.buycraft;

import me.matthewe.atherial.api.modules.buycraft.ban.Ban;
import me.matthewe.atherial.api.modules.buycraft.checkout.Checkout;
import me.matthewe.atherial.api.modules.buycraft.commandqueue.CommandQueue;
import me.matthewe.atherial.api.modules.buycraft.giftcard.GiftCard;
import me.matthewe.atherial.api.modules.buycraft.information.Information;
import me.matthewe.atherial.api.modules.buycraft.listings.Listings;
import me.matthewe.atherial.api.modules.buycraft.payments.Payment;

import java.util.List;

/**
 * Created by Matthew E on 2/4/2018.
 */
public interface BuyCraftApi {
    /**
     * @return The buycraft secret
     */
    String getSecret();

    /**
     * Updates bans
     */
    void updateBans();

    /**
     * @param user   The player username
     * @param ip     The player's ip address
     * @param reason The ban reason
     * @return The ban response from the  post request
     */
    Ban createBan(String user, String ip, String reason);

    /**
     * @param user   The player username
     * @param reason The ban reason
     * @return The ban response from the  post request
     */
    Ban createBan(String user, String reason);

    Checkout generateCheckout(String username, int packageId);

    Ban createBan(String user);

    void loadBanCache();

    void saveBanCache();

    void setSecret(String secret);

    Listings.BuyCraftPackage getBuyCraftPackage(int id);

    GiftCard createGiftCard(double amount, String note);

    GiftCard createGiftCard(double amount);

    GiftCard getGiftCard(int id);

    List<GiftCard> getCachedGiftCards();


    GiftCard voidGiftCard(int id);

    GiftCard topUpGiftCard(int id, String amount);

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
