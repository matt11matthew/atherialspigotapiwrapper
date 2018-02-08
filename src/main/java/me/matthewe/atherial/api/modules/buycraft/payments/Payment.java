package me.matthewe.atherial.api.modules.buycraft.payments;

import me.matthewe.atherial.api.modules.buycraft.BuyCraftPlayer;
import me.matthewe.atherial.api.modules.buycraft.information.Currency;

/**
 * Created by Matthew E on 2/6/2018.
 */
public class Payment {
    private int id;
    private double amount;
    private String date;
    private Currency currency;
    private BuyCraftPlayer player;
    private Package[] packages;

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BuyCraftPlayer getPlayer() {
        return player;
    }

    public Package[] getPackages() {
        return packages;
    }

    public static class Package {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
