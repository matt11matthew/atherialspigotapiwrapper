package me.matthewe.atherial.api.modules.buycraft;

import me.matthewe.atherial.api.modules.buycraft.commandqueue.CommandQueue;
import me.matthewe.atherial.api.modules.buycraft.information.Information;
import me.matthewe.atherial.api.modules.buycraft.listings.Listings;

/**
 * Created by Matthew E on 2/4/2018.
 */
public interface BuyCraftApi {
    String getSecret();

    void setSecret(String secret);

    Information getInformation();

    CommandQueue getCommandQueue();

    Listings getListings();

    void updateCache();

}
