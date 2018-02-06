package me.matthewe.atherial.api;

import me.matthewe.atherial.api.item.BookItem;
import me.matthewe.atherial.api.item.Item;
import me.matthewe.atherial.api.item.SkullItem;
import me.matthewe.atherial.api.module.Module;
import me.matthewe.atherial.api.module.Modules;
import me.matthewe.atherial.api.title.Title;

/**
 * Created by Matthew E on 2/4/2018.
 */
public interface AtherialApi {
    void init();

    SkullItem createSkullItem();

    void shutdown();

    BookItem createBookItem();

    Item createItem();

    Modules getModules();

    Title createTitle();

    void loadModules(Module... modules);

    <E extends Module> E getModule(Class<? extends Module> moduleClass);

    <E extends Module> boolean isModuleLoaded(Class<? extends Module> moduleClass);
}
