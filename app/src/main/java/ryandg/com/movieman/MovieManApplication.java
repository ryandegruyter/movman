package ryandg.com.movieman;

import android.app.Application;

import com.orhanobut.logger.Logger;

/**
 * Created by Ryan De Gruyter on 16/05/2015.
 */
public class MovieManApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();


        int x = 2;
        Logger.init(MovieManApplication.class.getSimpleName());
    }
}
