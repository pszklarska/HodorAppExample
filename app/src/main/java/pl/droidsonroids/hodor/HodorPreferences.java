package pl.droidsonroids.hodor;

import android.content.Context;
import android.content.SharedPreferences;

public class HodorPreferences {
    private static final String HODOR_PREFS = "hodor_prefs";

    private static final String BUNDLE_USERNAME = HODOR_PREFS + ".username";

    private final SharedPreferences mSharedPreferences;

    public HodorPreferences(Context context) {
        mSharedPreferences = context.getSharedPreferences(HODOR_PREFS, Context.MODE_PRIVATE);
    }

    public void setUsername(String username) {
        mSharedPreferences.edit().putString(BUNDLE_USERNAME, username).apply();
    }

    public String getUsername() {
        return mSharedPreferences.getString(BUNDLE_USERNAME, null);
    }
}
