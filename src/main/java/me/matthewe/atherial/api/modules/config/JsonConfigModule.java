package me.matthewe.atherial.api.modules.config;

import me.matthewe.atherial.api.module.ApiModule;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Matthew E on 2/10/2018.
 */
public class JsonConfigModule implements ApiModule {

    private Map<String, JsonFile> jsonFileMap;

    public JsonConfigModule() {
        this.jsonFileMap = new HashMap<>();
    }

    @Override
    public void enable() {

    }

    public JsonFile getJsonFile(String name) {
        return jsonFileMap.get(name);
    }

    @Override
    public void disable() {
        for (JsonFile jsonFile : jsonFileMap.values()) {
            jsonFile.save();
        }
    }

    public void loadJson(JsonFile jsonFile) {
        String replace = jsonFile.getFile().getName().replace(".json", "");
        if (!jsonFileMap.containsKey(replace)) {
            jsonFileMap.put(replace, jsonFile);
            jsonFile.load();
        }
    }

    public String getName() {
        return "JsonConfigModule";
    }
}
