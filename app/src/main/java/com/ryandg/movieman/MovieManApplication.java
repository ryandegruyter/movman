package com.ryandg.movieman;

import android.app.Application;
import android.graphics.Movie;
import android.os.Debug;
import android.util.Log;

import com.orhanobut.logger.Logger;
import com.ryandg.PrefUtils;
import com.ryandg.android.ConnectionUtils;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Ryan De Gruyter on 16/05/2015.
 */
public class MovieManApplication extends Application {

    public static final Boolean DEBUG_MODE = true;
    private static final String TAG = MovieManApplication.class.getSimpleName();


    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);

        PrefUtils.setUserAwareOfDrawer(this,false);
    }

}
