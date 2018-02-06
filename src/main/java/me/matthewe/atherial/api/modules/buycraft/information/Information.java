package me.matthewe.atherial.api.modules.buycraft.information;

/**
 * Created by Matthew E on 2/4/2018.
 */
public class Information {
    private Account account;
    private Server server;
    private Analytics analytics;

    public Analytics getAnalytics() {
        return analytics;
    }

    public Account getAccount() {
        return account;
    }

    public Server getServer() {
        return server;
    }
}
