package me.matthewe.atherial.api.modules.buycraft.commandqueue;

import com.fasterxml.jackson.annotation.JsonProperty;
import me.matthewe.atherial.api.modules.buycraft.BuyCraftPlayer;

/**
 * Created by Matthew E on 2/6/2018.
 */
public class CommandQueue {
    @JsonProperty("meta")
    private MetaBean metaBean;

    @JsonProperty("players")
    private BuyCraftPlayer[] players;

    public MetaBean getMetaBean() {
        return metaBean;
    }

    public BuyCraftPlayer[] getPlayers() {
        return players;
    }

    public static class MetaBean {
        @JsonProperty("execute_offline")
        private boolean executeOffline;

        private boolean more;

        @JsonProperty("next_check")
        private int nextCheck;

        public boolean isExecuteOffline() {
            return executeOffline;
        }

        public boolean isMore() {
            return more;
        }

        public int getNextCheck() {
            return nextCheck;
        }
    }

}
