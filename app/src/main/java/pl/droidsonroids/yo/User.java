package pl.droidsonroids.yo;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    public String username;
    public String token;

    public User() {
    }

    public User(final String username, final String token) {
        this.username = username;
        this.token = token;
    }
}
