package com.ryandg.Tmdb;

import com.ryandg.tmdb.TmdbImageSize;
import com.ryandg.tmdb.TmdbUtils;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Ryan De Gruyter on 25/05/2015.
 */
public class TestConfig {
    @Test
    public void testImagePath() throws Exception {
        String expectedPath = "https://image.tmdb.org/t/p/w500/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg";
        TmdbImageSize size = TmdbImageSize.SIZE_LARGE;
        String filePath = "/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg";

        String currentPath = TmdbUtils.getImgPath(size, filePath);
        Assert.assertEquals(expectedPath, currentPath);
    }
}
