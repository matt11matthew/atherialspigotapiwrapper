package me.matthewe.atherial.api.modules.placeholderapi;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.external.EZPlaceholderHook;
import me.matthewe.atherial.api.module.ApiWrapperModule;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;

/**
 * Created by Matthew E on 2/6/2018.
 */
public class PlaceHolderApiModule implements ApiWrapperModule {
    private org.bukkit.plugin.Plugin plugin;

    public PlaceHolderApiModule(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getPluginName() {
        return "PlaceholderAPI";
    }

    public String setPlaceHolders(Player player, String string) {
        if (PlaceholderAPI.containsPlaceholders(string)) {
            return PlaceholderAPI.setPlaceholders(player, string);
        }
        return string;
    }

    private void registerPlaceHolder(PlaceHolder placeHolder) {
        EZPlaceholderHook ezPlaceholderHook = new EZPlaceholderHook(plugin, placeHolder.getIdentifier()) {
            @Override
            public String onPlaceholderRequest(Player player, String s) {
                List<ChildPlaceHolder> childPlaceHolderList = placeHolder.getChildPlaceHolderList();
                if (!childPlaceHolderList.isEmpty()) {
                    for (ChildPlaceHolder childPlaceHolder : childPlaceHolderList) {
                        if (s.startsWith(childPlaceHolder.getIdentifier())) {
                            ChildPlaceHolderRequest childPlaceHolderRequest = childPlaceHolder.getChildPlaceHolderRequest();
                            if (childPlaceHolderRequest != null) {
                                return childPlaceHolderRequest.onPlaceholderRequest(player);
                            }
                        }
                    }
                }
                PlaceHolderRequest placeHolderRequest = placeHolder.getPlaceHolderRequest();
                if (placeHolderRequest != null) {
                    return placeHolderRequest.onPlaceholderRequest(player, s);
                }
                return "";
            }
        };
        ezPlaceholderHook.hook();
    }

    public void registerPlaceHolder(String identifier, PlaceHolderRequest placeHolderRequest) {
        this.registerPlaceHolder(new PlaceHolder(identifier, placeHolderRequest));
    }

    public void registerPlaceHolder(String identifier, PlaceHolderRequest placeHolderRequest, ChildPlaceHolder... childPlaceHolders) {
        this.registerPlaceHolder(new PlaceHolder(identifier, placeHolderRequest, childPlaceHolders));
    }

    public void registerPlaceHolder(String identifier,  ChildPlaceHolder... childPlaceHolders) {
        this.registerPlaceHolder(new PlaceHolder(identifier, childPlaceHolders));
    }

    @Override
    public String getName() {
        return "PlaceHolderApiModule";
    }
}
