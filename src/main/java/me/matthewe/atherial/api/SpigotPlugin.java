package me.matthewe.atherial.api;

import me.matthewe.atherial.api.modules.buycraft.AtherialBuyCraftApi;
import me.matthewe.atherial.api.modules.buycraft.BuyCraftApi;
import me.matthewe.atherial.api.modules.buycraft.information.Information;
import me.matthewe.atherial.api.modules.placeholderapi.ChildPlaceHolder;
import me.matthewe.atherial.api.modules.placeholderapi.PlaceHolderApiModule;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Matthew E on 2/4/2018.
 */
public class SpigotPlugin extends JavaPlugin {
    private static AtherialApi atherialApi;
    private final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger("AtherialApiTest");

    @Override
    public void onEnable() {
        atherialApi = new SpigotAtherialApi(this);

        atherialApi.loadModules(new PlaceHolderApiModule(this), new AtherialBuyCraftApi());

        if (atherialApi.isModuleLoaded(PlaceHolderApiModule.class)) {
            PlaceHolderApiModule placeHolderApiModule = atherialApi.getModule(PlaceHolderApiModule.class);

            placeHolderApiModule.registerPlaceHolder("matt11matthew",
                    new ChildPlaceHolder("staff_count", player -> "1"),
                    new ChildPlaceHolder("staff_list", HumanEntity::getName));

            Player matt11matthew = Bukkit.getPlayer("matt11matthew");

            String message = placeHolderApiModule.setPlaceHolders(matt11matthew, "%matt11matthew_staff_count% %matt11matthew_staff_list%");
            matt11matthew.sendMessage(message);
        }


        BuyCraftApi buyCraftApi = atherialApi.getModule(AtherialBuyCraftApi.class);
        buyCraftApi.setSecret("e305159db019b531d94f93484f01dbcf297d09e3");

        buyCraftApi.updateCache();
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, buyCraftApi::updateCache, 4000L, 4000L);

        Information information = buyCraftApi.getInformation();
        System.out.println(information.getAccount().getName());

    }

    @Override
    public void onDisable() {
        atherialApi.shutdown();
    }
}
