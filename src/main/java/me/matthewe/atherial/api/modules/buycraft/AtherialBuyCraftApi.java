package me.matthewe.atherial.api.modules.buycraft;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import me.matthewe.atherial.api.module.ApiModule;
import me.matthewe.atherial.api.modules.buycraft.commandqueue.CommandQueue;
import me.matthewe.atherial.api.modules.buycraft.information.Information;
import me.matthewe.atherial.api.modules.buycraft.listings.Listings;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Matthew E on 2/4/2018.
 */
public class AtherialBuyCraftApi implements BuyCraftApi, ApiModule {
    private static final String BUYCRAFT_SECRET_IDENTIFIER = "X-Buycraft-Secret";
    private String secret;
    private final String BASE_URL = "https://plugin.buycraft.net";
    private Information information;
    private CommandQueue commandQueue;
    private Listings listings;
    private ObjectMapper objectMapper;

    public AtherialBuyCraftApi() {
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
    }

    @Override
    public String getSecret() {
        return secret;
    }

    @Override
    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public Information getInformation() {
        return information;
    }

    @Override
    public CommandQueue getCommandQueue() {
        return commandQueue;
    }

    @Override
    public Listings getListings() {
        return listings;
    }

    public <E extends Object> E parseObjectFromJson(String json, Class<E> aClass) {
        try {
            Object o = objectMapper.readValue(json, aClass);
            return (E) o;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateCache() {
        Map<String, String> headers = new HashMap<>();
        headers.put(BUYCRAFT_SECRET_IDENTIFIER, this.secret);

        Connection.Response informationResponse = getResponse(BASE_URL + "/information", new HashMap<>(), headers);
        if (informationResponse != null) {
            this.information = parseObjectFromJson(informationResponse.body(), Information.class);
        }

        Connection.Response commandQueueResponse = getResponse(BASE_URL + "/queue", new HashMap<>(), headers);
        if (commandQueueResponse != null) {
            this.commandQueue = parseObjectFromJson(commandQueueResponse.body(), CommandQueue.class);
        }

        Connection.Response listingsResponse = getResponse(BASE_URL + "/listing", new HashMap<>(), headers);
        if (listingsResponse != null) {
            this.listings = parseObjectFromJson(listingsResponse.body(), Listings.class);
        }
    }

    private Connection.Response getResponse(String url, Map<String, String> parmas, Map<String, String> headers) {
        try {
            return Jsoup
                    .connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.107 Safari/537.36")
                    .ignoreContentType(true)
                    .data(parmas)
                    .method(Connection.Method.GET)
                    .ignoreHttpErrors(true)
                    .headers(headers)
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getName() {
        return "BuyCraftModule";
    }
}