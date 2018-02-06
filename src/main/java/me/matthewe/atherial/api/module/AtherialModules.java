package me.matthewe.atherial.api.module;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by Matthew E on 2/6/2018.
 */
public class AtherialModules implements Modules {
    private final Map<String, Module> modules;

    public AtherialModules() {
        this.modules = new HashMap<>();
    }

    @Override
    public void addModule(Module module) {
        if (!modules.containsKey(module.getName())) {
            modules.put(module.getName(), module);

        }
    }

    @Override
    public void forEach(Consumer<? super Module> action) {
        modules.values().forEach(action);
    }

    @Override
    public Iterator<Module> iterator() {
        return modules.values().iterator();
    }
}
