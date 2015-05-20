package com.ryandg.movieman;

import android.app.Application;
import android.graphics.Movie;

import com.orhanobut.logger.Logger;

/**
 * Created by Ryan De Gruyter on 16/05/2015.
 */
public class MovieManApplication extends Application {

    public static final Boolean DEBUG_MODE = true;

    @Override
    public void onCreate() {
        super.onCreate();

        Logger.init(MovieManApplication.class.getSimpleName());
    }
}
