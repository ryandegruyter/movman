package com.ryandg.android;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Ryan on 13/05/2015.
 *
 */
public class ConnectionUtils {

    public static boolean isNetworkReachable(Context context) {
        final NetworkInfo activeNetworkInfo = getActiveNetworkInfo(context);

        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    private static NetworkInfo getActiveNetworkInfo(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo();
    }

}