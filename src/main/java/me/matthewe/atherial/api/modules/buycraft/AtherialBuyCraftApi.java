package me.matthewe.atherial.api.modules.buycraft;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import me.matthewe.atherial.api.module.ApiModule;
import me.matthewe.atherial.api.modules.buycraft.ban.Ban;
import me.matthewe.atherial.api.modules.buycraft.ban.DataBan;
import me.matthewe.atherial.api.modules.buycraft.checkout.Checkout;
import me.matthewe.atherial.api.modules.buycraft.commandqueue.CommandQueue;
import me.matthewe.atherial.api.modules.buycraft.events.BuyCraftBanEvent;
import me.matthewe.atherial.api.modules.buycraft.events.BuyCraftPaymentEvent;
import me.matthewe.atherial.api.modules.buycraft.giftcard.GiftCard;
import me.matthewe.atherial.api.modules.buycraft.giftcard.GiftCardData;
import me.matthewe.atherial.api.modules.buycraft.giftcard.GiftCardListData;
import me.matthewe.atherial.api.modules.buycraft.information.Information;
import me.matthewe.atherial.api.modules.buycraft.listings.Listings;
import me.matthewe.atherial.api.modules.buycraft.payments.Payment;
import org.apache.commons.io.FileUtils;
import org.bukkit.plugin.Plugin;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

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
    private me.matthewe.atherial.api.modules.buycraft.ban.BanList banList;
    private List<Payment> payments;
    private ObjectMapper objectMapper;
    private List<Integer> completedPaymentIds;
    private List<Integer> completedBanIds;
    private List<Payment> eventPayments;
    private List<GiftCard> giftCards;
    private List<Ban> eventBans;
    private Plugin plugin;

    public AtherialBuyCraftApi(Plugin plugin) {
        this.plugin = plugin;
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        this.payments = new ArrayList<>();
        this.eventPayments = new ArrayList<>();
        this.eventBans = new ArrayList<>();
        this.giftCards = new ArrayList<>();
        this.completedPaymentIds = new ArrayList<>();
        this.completedBanIds = new ArrayList<>();
    }

    @Override
    public void enable() {
        File folder = new File(plugin.getDataFolder() + "/buycraft/");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        loadPaymentCache();
        loadBanCache();
    }

    @Override
    public void disable() {
        savePaymentCache();
        saveBanCache();
    }

    @Override
    public String getSecret() {
        return secret;
    }

    @Override
    public Ban createBan(String user, String ip, String reason) {
        Map<String, String> headers = new HashMap<>();
        Map<String, String> parmas = new HashMap<>();

        headers.put(BUYCRAFT_SECRET_IDENTIFIER, this.secret);

        if (ip != null) {
            parmas.put("ip", ip);
        }
        if (reason != null) {
            parmas.put("reason", reason);
        }

        Connection.Response post = post(BASE_URL + "/bans", parmas, headers);
        if (post != null) {
            DataBan dataBan = parseObjectFromJson(post.body(), DataBan.class);
            return dataBan.getBan();
        }
        return null;
    }

    @Override
    public Ban createBan(String user, String reason) {
        return createBan(user, null, reason);
    }

    @Override
    public Checkout generateCheckout(String username, int packageId) {
        Map<String, String> headers = new HashMap<>();
        Map<String, String> parmas = new HashMap<>();
        parmas.put("username", username);
        parmas.put("package_id", packageId + "");
        headers.put(BUYCRAFT_SECRET_IDENTIFIER, this.secret);
        Connection.Response response = post(BASE_URL + "/checkout", parmas, headers);
        if (response != null) {
            return parseObjectFromJson(response.body(), Checkout.class);
        }
        return null;
    }

    @Override
    public Ban createBan(String user) {
        return createBan(user, null, null);
    }

    @Override
    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public Listings.BuyCraftPackage getBuyCraftPackage(int id) {
        Map<Integer, Listings.BuyCraftPackage> buyCraftPackages = new HashMap<>();
        for (Listings.Category listing : listings) {
            for (Listings.BuyCraftPackage buyCraftPackage : listing.getPackages()) {
                buyCraftPackages.put(buyCraftPackage.getId(), buyCraftPackage);
            }
            for (Listings.Category.SubCategory subCategory : listing.getSubCategories()) {
                for (Listings.BuyCraftPackage buyCraftPackage : subCategory.getPackages()) {
                    buyCraftPackages.put(buyCraftPackage.getId(), buyCraftPackage);
                }
            }
        }
        return buyCraftPackages.get(id);
    }

    @Override
    public List<GiftCard> getCachedGiftCards() {
        return giftCards;
    }

    @Override
    public GiftCard createGiftCard(double amount, String note) {
        Map<String, String> headers = new HashMap<>();
        Map<String, String> parmas = new HashMap<>();
        parmas.put("amount", String.valueOf(amount));
        if (note != null) {
            parmas.put("note", note);
        }
        headers.put(BUYCRAFT_SECRET_IDENTIFIER, this.secret);
        Connection.Response response = post(BASE_URL + "/gift-cards/", parmas, headers);
        if (response != null) {
            return parseObjectFromJson(response.body(), GiftCardData.class).getGiftCard();
        }
        return null;
    }

    @Override
    public GiftCard createGiftCard(double amount) {
        return createGiftCard(amount, null);
    }

    @Override
    public GiftCard getGiftCard(int id) {
        Map<String, String> headers = new HashMap<>();
        headers.put(BUYCRAFT_SECRET_IDENTIFIER, this.secret);
        Connection.Response response = getResponse(BASE_URL + "/gift-cards/" + id, new HashMap<>(), headers);
        if (response != null) {
            return parseObjectFromJson(response.body(), GiftCardData.class).getGiftCard();
        }
        return null;
    }

    @Override
    public GiftCard voidGiftCard(int id) {
        Map<String, String> headers = new HashMap<>();
        Map<String, String> parmas = new HashMap<>();

        headers.put(BUYCRAFT_SECRET_IDENTIFIER, this.secret);
        Connection.Response response = delete(BASE_URL + "/gift-cards/" + id, parmas, headers);
        if (response != null) {
            return parseObjectFromJson(response.body(), GiftCardData.class).getGiftCard();
        }
        return null;
    }

    @Override
    public GiftCard topUpGiftCard(int id, String amount) {
        Map<String, String> headers = new HashMap<>();
        Map<String, String> parmas = new HashMap<>();
        parmas.put("amount", String.valueOf(amount));

        headers.put(BUYCRAFT_SECRET_IDENTIFIER, this.secret);
        Connection.Response response = put(BASE_URL + "/gift-cards/" + id, parmas, headers);
        if (response != null) {
            return parseObjectFromJson(response.body(), GiftCardData.class).getGiftCard();
        }
        return null;
    }

    @Override
    public void callEvents() {
        for (Payment payment : eventPayments) {

            BuyCraftPaymentEvent buyCraftPaymentEvent = new BuyCraftPaymentEvent(payment, this);
            plugin.getServer().getPluginManager().callEvent(buyCraftPaymentEvent);
        }

        for (Ban ban : eventBans) {

            BuyCraftBanEvent buyCraftBanEvent = new BuyCraftBanEvent(ban, this);
            plugin.getServer().getPluginManager().callEvent(buyCraftBanEvent);
        }
        eventPayments.clear();
        eventBans.clear();
    }

    @Override
    public Information getInformation() {
        return information;
    }

    @Override
    public void loadPaymentCache() {
        try {
            File paymentCacheFile = new File(plugin.getDataFolder() + "/buycraft/", "payments.txt");
            if (!paymentCacheFile.exists()) {
                paymentCacheFile.createNewFile();
            }
            String readFileToString = FileUtils.readFileToString(paymentCacheFile, Charset.defaultCharset());
            for (String s : readFileToString.split("\n")) {
                try {
                    int parseInt = Integer.parseInt(s.trim());
                    if (!completedPaymentIds.contains(parseInt)) {
                        completedPaymentIds.add(Integer.parseInt(s.trim()));
                    }
                } catch (NumberFormatException e) {
                    continue;
                }
            }
            System.out.println("Loaded " + completedPaymentIds.size() + " payments.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadBanCache() {
        try {
            File paymentCacheFile = new File(plugin.getDataFolder() + "/buycraft/", "bans.txt");
            if (!paymentCacheFile.exists()) {
                paymentCacheFile.createNewFile();
            }
            String readFileToString = FileUtils.readFileToString(paymentCacheFile, Charset.defaultCharset());
            for (String s : readFileToString.split("\n")) {
                try {
                    int parseInt = Integer.parseInt(s.trim());
                    if (!completedBanIds.contains(parseInt)) {
                        completedBanIds.add(Integer.parseInt(s.trim()));
                    }
                } catch (NumberFormatException e) {
                    continue;
                }
            }
            System.out.println("Loaded " + completedBanIds.size() + " bans.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveBanCache() {
        try {
            File paymentCacheFile = new File(plugin.getDataFolder() + "/buycraft/", "bans.txt");
            if (paymentCacheFile.exists()) {
                paymentCacheFile.delete();
            }
            paymentCacheFile.createNewFile();
            FileUtils.writeLines(paymentCacheFile, completedBanIds);
            System.out.println("Saved " + completedBanIds.size() + " bans.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void savePaymentCache() {
        try {
            File paymentCacheFile = new File(plugin.getDataFolder() + "/buycraft/", "payments.txt");
            if (paymentCacheFile.exists()) {
                paymentCacheFile.delete();
            }
            paymentCacheFile.createNewFile();
            FileUtils.writeLines(paymentCacheFile, completedPaymentIds);
            System.out.println("Saved " + completedPaymentIds.size() + " payments.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public me.matthewe.atherial.api.modules.buycraft.ban.BanList getBanList() {
        return banList;
    }

    @Override
    public CommandQueue getCommandQueue() {
        return commandQueue;
    }

    @Override
    public Listings getListings() {
        return listings;
    }

    @Override
    public List<Payment> getCurrentCachedPayments() {
        return payments;
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


        Connection.Response giftCardResponse = getResponse(BASE_URL + "/gift-cards", new HashMap<>(), headers);
        if (giftCardResponse != null) {
            this.giftCards = new ArrayList<>();
            this.giftCards.addAll(Arrays.asList(parseObjectFromJson(giftCardResponse.body(), GiftCardListData.class).getGiftCards()));
        }

        Connection.Response banListResponse = getResponse(BASE_URL + "/bans", new HashMap<>(), headers);
        if (banListResponse != null) {
            this.banList = parseObjectFromJson(banListResponse.body(), me.matthewe.atherial.api.modules.buycraft.ban.BanList.class);
        }

    }

    @Override
    public void updateBans() {
        Map<String, String> headers = new HashMap<>();
        headers.put(BUYCRAFT_SECRET_IDENTIFIER, this.secret);

        Connection.Response banListResponse = getResponse(BASE_URL + "/bans", new HashMap<>(), headers);
        if (banListResponse != null) {
            this.banList = parseObjectFromJson(banListResponse.body(), me.matthewe.atherial.api.modules.buycraft.ban.BanList.class);
        }

        for (Ban ban : banList.getBans()) {
            if (!completedBanIds.contains(ban.getId())) {
                completedBanIds.add(ban.getId());
                eventBans.add(ban);
            }
        }
    }

    @Override
    public void updatePayments() {
        Map<String, String> headers = new HashMap<>();
        headers.put(BUYCRAFT_SECRET_IDENTIFIER, this.secret);
        Connection.Response paymentsResponse = getResponse(BASE_URL + "/payments?limit=20", new HashMap<>(), headers);
        if (paymentsResponse != null) {
            String body = paymentsResponse.body();
            JsonArray asJsonArray = new JsonParser().parse(body).getAsJsonArray();
            for (int i = 0; i < asJsonArray.size(); i++) {
                JsonElement jsonElement = asJsonArray.get(i);
                Payment payment = parseObjectFromJson(jsonElement.toString(), Payment.class);
                if (!completedPaymentIds.contains(payment.getId())) {
                    completedPaymentIds.add(payment.getId());
                    payments.add(payment);
                    eventPayments.add(payment);
                }
            }
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

    private Connection.Response delete(String url, Map<String, String> parmas, Map<String, String> headers) {
        try {
            return Jsoup
                    .connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.107 Safari/537.36")
                    .ignoreContentType(true)
                    .data(parmas)
                    .method(Connection.Method.DELETE)
                    .ignoreHttpErrors(true)
                    .headers(headers)
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Connection.Response put(String url, Map<String, String> parmas, Map<String, String> headers) {
        try {
            return Jsoup
                    .connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.107 Safari/537.36")
                    .ignoreContentType(true)
                    .data(parmas)
                    .method(Connection.Method.PUT)
                    .ignoreHttpErrors(true)
                    .headers(headers)
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Connection.Response post(String url, Map<String, String> parmas, Map<String, String> headers) {
        try {
            return Jsoup
                    .connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.107 Safari/537.36")
                    .ignoreContentType(true)
                    .data(parmas)
                    .method(Connection.Method.POST)
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