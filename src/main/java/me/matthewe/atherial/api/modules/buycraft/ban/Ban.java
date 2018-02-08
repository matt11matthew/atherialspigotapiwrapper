package me.matthewe.atherial.api.modules.buycraft.ban;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Matthew E on 2/6/2018.
 */
public class Ban {
    private int id;
    private String time;
    private String ip;
    @JsonProperty("payment_email")
    private String paymentEmail;
    private String reason;
    private User user;

    public int getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public String getIp() {
        return ip;
    }

    public String getPaymentEmail() {
        return paymentEmail;
    }

    public String getReason() {
        return reason;
    }

    public User getUser() {
        return user;
    }

    public static class User {
        private String ign;
        private String uuid;

        public String getIgn() {
            return ign;
        }

        public String getUuid() {
            return uuid;
        }
    }
}
