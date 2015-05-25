package com.ryandg.tmdb;

/**
 * Created by Ryan De Gruyter on 25/05/2015.
 *
 */
public enum TmdbImageSize {
    SIZE_XXXSMALL("w45"),
    SIZE_XXSMALL("w92"),
    SIZE_XSMALL("w154"),
    SIZE_SMALL("w185"),
    SIZE_MEDIUM("w342"),
    SIZE_LARGE("w500"),
    SIZE_XLARGE("w780"),
    SIZE_XXLARGE("w1280"),
    SIZE_ORIGINAL("original");

    final String size;

    TmdbImageSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }
}
