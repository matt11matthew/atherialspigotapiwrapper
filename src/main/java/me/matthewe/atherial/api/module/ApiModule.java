package me.matthewe.atherial.api.module;

/**
 * Created by Matthew E on 2/6/2018.
 */
public interface ApiModule extends Module {

    default ModuleType getModuleType() {
        return ModuleType.API;
    }
}
