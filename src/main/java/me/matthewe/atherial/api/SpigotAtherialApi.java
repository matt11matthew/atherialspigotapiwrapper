package me.matthewe.atherial.api;

import me.matthewe.atherial.api.item.*;
import me.matthewe.atherial.api.module.ApiWrapperModule;
import me.matthewe.atherial.api.module.AtherialModules;
import me.matthewe.atherial.api.module.Module;
import me.matthewe.atherial.api.module.Modules;
import me.matthewe.atherial.api.title.AtherialTitle;
import me.matthewe.atherial.api.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;

/**
 * Created by Matthew E on 2/4/2018.
 */
public class SpigotAtherialApi implements AtherialApi {
    private Plugin plugin;
    private Modules modules;
    private final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger("SpigotAtherialApi");

    public SpigotAtherialApi(Plugin plugin) {
        this.plugin = plugin;
        this.init();
    }

    @Override
    public void init() {
        this.modules = new AtherialModules();
    }

    @Override
    public SkullItem createSkullItem() {
        return (SkullItem) new AtherialSkullItem().type(Material.SKULL_ITEM);
    }

    @Override
    public void shutdown() {
        for (Module module : this.modules) {
            module.disable();
        }
    }

    @Override
    public BookItem createBookItem() {
        return (BookItem) new AtherialBookItem().type(Material.WRITTEN_BOOK);
    }

    @Override
    public Item createItem() {
        return new AtherialItem();
    }

    @Override
    public Modules getModules() {
        return modules;
    }

    @Override
    public Title createTitle() {
        return new AtherialTitle();
    }

    @Override
    public void loadModules(Module... modules1) {
        for (Module module : modules1) {
            if (module instanceof ApiWrapperModule) {
                ApiWrapperModule apiWrapperModule = (ApiWrapperModule) module;
                if (!Bukkit.getPluginManager().isPluginEnabled(apiWrapperModule.getPluginName())) {
                    LOGGER.error("Could not load module " + module.getName() + " because the plugin " + ((ApiWrapperModule) module).getPluginName() + " is disabled");
                    continue;
                }
            }
            modules.addModule(module);
        }
        for (Module module : modules) {
            module.enable();
        }
    }

    @Override
    public <E extends Module> E getModule(Class<? extends Module> moduleClass) {
        for (Module module : modules) {
            if (moduleClass.getName().equals(module.getClass().getName())) {
                return (E) module;
            }
        }
        return null;
    }

    @Override
    public <E extends Module> boolean isModuleLoaded(Class<? extends Module> moduleClass) {
        E module = (E) getModule(moduleClass);
        if (module != null) {
            for (Module module1 : modules) {
                if (module.equals(module1)) {
                    return true;
                }
            }
        }
        return false;
    }
}
