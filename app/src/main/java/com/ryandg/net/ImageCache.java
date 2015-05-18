package com.ryandg.net;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by Ryan De Gruyter on 18/05/2015.
 */
public class ImageCache implements ImageLoader.ImageCache {

    private LruCache<String, Bitmap> mCache;

    public ImageCache(int size) {
        mCache = new LruCache<>(size);
    }

    @Override
    public Bitmap getBitmap(String url) {
        return mCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        mCache.put(url, bitmap);
    }
}
