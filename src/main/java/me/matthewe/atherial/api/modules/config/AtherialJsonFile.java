package me.matthewe.atherial.api.modules.config;

import com.google.gson.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Matthew E on 2/10/2018.
 */
public abstract class AtherialJsonFile<K extends Object> extends JsonFile {

    private boolean save;

    public AtherialJsonFile(File file, boolean save) {
        super(file);
        this.save = save;
    }

    public void addDefaults() {
    }

    public void forceSave() {
        try {
            if (!this.file.exists()) {
                return;
            }
            this.file.delete();

            this.file.createNewFile();
            FileUtils.writeStringToFile(this.file, new GsonBuilder().setPrettyPrinting().create().toJson(root));
            onSave();
        } catch (IOException var2) {
            var2.printStackTrace();
        }
    }

    public void onLoad() {

    }

    public void onSave() {
    }

    @Override
    public void save() {
        if (!save) {
            return;
        }
        forceSave();
    }



    @Override
    public void load() {
        super.load();
        addDefaults();
        onLoad();
    }

    public <V> AtherialJsonFile<K> add(AddBuilder<V> addBuilder) {
        root.add(addBuilder.name, addBuilder.toJsonElement());
        return this;
    }

    public <V> AtherialJsonFile addDefault(AddBuilder<V> addBuilder) {
        if (!root.has(addBuilder.name)) {
            root.add(addBuilder.name, addBuilder.toJsonElement());
        }
        return this;
    }

    public <E> Map<K, E> get(String name, Class<E> vClass) {
        GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting().setFieldNamingStrategy(new FieldNamingStrategy() {
            @Override
            public String translateName(Field field) {
                if (field.getName().equalsIgnoreCase(name)) {
                    return name;
                }
                switch (field.getName()) {
                    case "kvMap":
                        return name;
                    default:
                        return field.getName();
                }
            }
        });
        Map<K, E> kvMap = new ConcurrentHashMap<>();
        for (JsonElement jsonElement : root.get(name).getAsJsonArray()) {
            JsonObject asJsonObject = jsonElement.getAsJsonObject();
            for (Map.Entry<String, JsonElement> stringJsonElementEntry : asJsonObject.entrySet()) {
                kvMap.put((K) stringJsonElementEntry.getKey(), gsonBuilder.create().fromJson(asJsonObject.get(stringJsonElementEntry.getKey()), vClass));
            }
        }
        return kvMap;
    }

    public boolean isSave() {
        return save;
    }

    public AtherialJsonFile<K> setSave(boolean save) {
        this.save = save;
        return this;
    }

    public class AddBuilder<V> {

        private String name;

        private Map<K, V> kvMap;
        private Class<V> vClass;

        public AddBuilder(String name, Class<V> vClass) {
            this.name = name;
            this.vClass = vClass;
            this.kvMap = new ConcurrentHashMap<>();
        }

        public JsonArray toJsonElement() {
            GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting().setFieldNamingStrategy(new FieldNamingStrategy() {
                @Override
                public String translateName(Field field) {
                    switch (field.getName()) {
                        case "kvMap":
                            return name;
                        default:
                            return field.getName();
                    }
                }
            });
            JsonArray jsonElements = new JsonArray();
            kvMap.forEach((k, object) -> {
                JsonObject jsonObject = new JsonObject();
                jsonObject.add(String.valueOf(k), new JsonParser().parse(gsonBuilder.create().toJson(object)));
                jsonElements.add(jsonObject);
            });
            return jsonElements;
        }

        public AddBuilder<V> add(K k, V v) {
            if (kvMap.containsKey(k)) {
                kvMap.remove(k);
            }
            kvMap.put(k, v);
            return this;
        }

        public Map<K, V> build() {
            return kvMap;
        }

        public Class<V> getvClass() {
            return vClass;
        }
    }
}
