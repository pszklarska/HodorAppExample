package pl.droidsonroids.hodor.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.gson.annotations.SerializedName;

@IgnoreExtraProperties
public class User {
    @SerializedName("username") public String username;
    @SerializedName("token") public String deviceToken;

    public User() {
        //no-op
    }

    public User(final String username, final String token) {
        this.username = username;
        this.deviceToken = token;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return deviceToken;
    }
}
