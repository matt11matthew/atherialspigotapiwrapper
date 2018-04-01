package me.matthewe.atherial.api.modules.config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by Matthew E on 2/10/2018.
 */
public abstract class JsonFile {
    protected File file;
    protected JsonObject root;

    public JsonFile(File file) {
        this.file = file;
    }

    public File getFile() {

        return file;
    }

    public void save() {
        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            FileUtils.writeStringToFile(file, root.toString());
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void load() {
        try {
            String readFileToString = FileUtils.readFileToString(file, Charset.defaultCharset());
            this.root = new JsonParser().parse(readFileToString).getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
