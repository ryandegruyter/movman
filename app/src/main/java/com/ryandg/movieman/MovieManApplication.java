package com.ryandg.movieman;

import android.app.Application;

import com.orhanobut.logger.Logger;

/**
 * Created by Ryan De Gruyter on 16/05/2015.
 */
public class MovieManApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Logger.init(MovieManApplication.class.getSimpleName());
    }
}
