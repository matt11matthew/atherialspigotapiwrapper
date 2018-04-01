package me.matthewe.atherial.api;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Matthew E on 2/4/2018.
 */
public class SpigotPlugin extends JavaPlugin implements Listener {
    private static AtherialApi atherialApi;
    private final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger("AtherialApiTest");

    @Override
    public void onEnable() {
        atherialApi = new SpigotAtherialApi(this);
        Bukkit.getServer().getPluginManager().registerEvents(this, this);

//        atherialApi.loadModules(new PlaceHolderApiModule(this), new AtherialBuyCraftApi(this));
//
//
//        if (atherialApi.isModuleLoaded(AtherialBuyCraftApi.class)) {
//            BuyCraftApi buyCraftApi = atherialApi.getModule(AtherialBuyCraftApi.class);
//            buyCraftApi.setSecret("0d5f01e122376454345594096abaa28390b961e1");
//
//            buyCraftApi.updateCache();
//            Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, buyCraftApi::updateCache, 4000L, 4000L);
//            Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, buyCraftApi::updatePayments, 250L, 250L);
//            Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, buyCraftApi::updateBans, 20L, 250L);
//            Bukkit.getScheduler().scheduleSyncRepeatingTask(this, buyCraftApi::callEvents, 20L, 20L);
//
//            Information information = buyCraftApi.getInformation();
//            System.out.println(information.getAccount().getName());
//            Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
//                for (Ban ban : buyCraftApi.getBanList().getBans()) {
//                    System.out.println(ban.getUser().getIgn());
//                }
//                }, 20L, 20L);
//        }
//        if (atherialApi.isModuleLoaded(PlaceHolderApiModule.class)) {
//            PlaceHolderApiModule placeHolderApiModule = atherialApi.getModule(PlaceHolderApiModule.class);
//
//            placeHolderApiModule.registerPlaceHolder("buycraft",
//                    new ChildPlaceHolder("total_money_earned", player -> "UNKNOWN"));
//        }

    }

//    @EventHandler
//    public void onBuyCraftPayment(BuyCraftPaymentEvent event) {
//        Payment payment = event.getPayment();
//        BuyCraftPlayer player = payment.getPlayer();
//        BuyCraftApi buyCraftApi = event.getBuyCraftApi();
//        if (player != null) {
//            Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "Thank you " + player.getName() + " for purchasing from the store");
//            for (Payment.Package aPackage : payment.getPackages()) {
//                Listings.BuyCraftPackage buyCraftPackage = buyCraftApi.getBuyCraftPackage(aPackage.getId());
//                Bukkit.getServer().broadcastMessage(ChatColor.GRAY + "-" + aPackage.getName() + " " + payment.getCurrency().getSymbol() + buyCraftPackage.getPrice() + " " + payment.getCurrency().getSymbol());
//            }
//        }
//    }
//
//    @EventHandler
//    public void onBuyCraftBan(BuyCraftBanEvent event) {
//        System.out.println("test");
//        Ban ban = event.getBan();
//        Bukkit.getServer().broadcastMessage(ChatColor.RED + ban.getUser().getIgn() + " has been banned from buycraft for " + ban.getReason());
//    }

    @Override
    public void onDisable() {
        atherialApi.shutdown();
    }
}
