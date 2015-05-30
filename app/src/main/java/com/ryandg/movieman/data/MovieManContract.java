package com.ryandg.movieman.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Ryan De Gruyter on 26/05/2015.
 */
public class MovieManContract {

    //    naam content provider
    public static final String CONTENT_AUTHORITY = "com.ryandg.movieman.app";

    //    basis uri van de content provider
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final class MovieManUser implements BaseColumns {
        public static final String PATH_USER = "user";
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_USER).build();
        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_USER;
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_USER;

        public static final String TABLE_NAME = PATH_USER;

        interface Columns {
            String COL_USERNAME = "username";
            String COL_PASSWORD = "password";
            String COL_DATE_CREATED = "date_created";
        }
    }
}
