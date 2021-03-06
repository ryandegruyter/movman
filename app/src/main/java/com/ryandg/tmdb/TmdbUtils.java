package com.ryandg.tmdb;

/**
 * Created by Ryan on 11/05/2015.
 */
public class TmdbUtils {
    public static final String POPULAR_MOVIES = "https://api.themoviedb.org/3/movie/popular?api_key=%s";

    public static final String getImgPath(TmdbImageSize size, String imgPath) {
        return TmdbConfiguration.secureBaseUrl + size.getSize() + imgPath;
    }
}
