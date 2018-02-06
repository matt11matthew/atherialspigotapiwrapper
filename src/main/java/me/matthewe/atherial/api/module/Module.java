package me.matthewe.atherial.api.module;


/**
 * Created by Matthew E on 2/6/2018.
 */
public interface Module {
    default void enable() {}

    default void disable() {}

    String getName();

    ModuleType getModuleType();
}
