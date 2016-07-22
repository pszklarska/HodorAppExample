package pl.droidsonroids.hodor.model;

import com.google.gson.annotations.SerializedName;

public class FCMMessage {

    public static final String USERNAME = "username";

    private String to;
    private Data data;

    public FCMMessage(final String toDeviceToken, final String fromUsername) {
        this.to = toDeviceToken;
        this.data = new Data(fromUsername);
    }

    private class Data {

        public Data(final String username) {
            this.username = username;
        }

        @SerializedName(USERNAME) private String username;
    }
}
