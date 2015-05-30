package com.ryandg;

import android.content.Context;
import android.content.SharedPreferences;

import com.ryandg.movieman.pojo.MovieManUser;

/**
 * Created by Ryan on 19/05/2015.
 */
public class PrefUtils {
    public static final String PREF_NAME = "moviemanprefs";

    public static final String KEY_AWARE_OF_DRAWER = "key_aware_of_drawer";
    private static final String KEY_LOGGED_IN = "key_usr_logged_in";
    private static final String PREF_USER_ID = "key_usr_id";
    private static final String PREF_USER_NAME = "key_usr_name";
    private static String currentUser;


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

    public static void createSession(Context context, MovieManUser user) {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                .edit().putInt(PREF_USER_ID, user.getId())
                .putString(PREF_USER_NAME, user.getUserName())
                .apply();
    }

    public static String getCurrentUser(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                .getString(PREF_USER_NAME, "ERROR");
    }
}
