package com.ryandg;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Ryan on 19/05/2015.
 */
public class PrefUtils {
    public static final String PREF_NAME = "moviemanprefs";

    public static final String KEY_AWARE_OF_DRAWER = "key_aware_of_drawer";
    private static final String KEY_LOGGED_IN = "key_usr_logged_in";

    public static Boolean isUserLoggedIn(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                .getBoolean(KEY_LOGGED_IN, false);
    }

    public static void markUserLoggedIn(boolean value, Context context) {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                .edit().putBoolean(KEY_LOGGED_IN, value).commit();
    }

    public static Boolean isUserAwareOfDrawer(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                .getBoolean(KEY_AWARE_OF_DRAWER, false);

    }

    public static final void setUserAwareOfDrawer(Context context, boolean isAware) {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(KEY_AWARE_OF_DRAWER, isAware)
                .apply();
    }
}
