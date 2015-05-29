package com.ryandg.movieman.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.test.AndroidTestCase;

import com.ryandg.PrefUtils;
import com.ryandg.movieman.MovieManApplication;
import com.ryandg.movieman.account.AuthenticationException;

/**
 * Created by Ryan De Gruyter on 27/05/2015.
 */
public class LoginTest extends AndroidTestCase {

    public void testUserIsNotLoggedIn() throws Exception {
        final Boolean userLoggedIn = PrefUtils.isUserLoggedIn(getContext());
        assertFalse(userLoggedIn);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        getContext().getSharedPreferences(PrefUtils.PREF_NAME, Context.MODE_PRIVATE)
                .edit().clear().commit();
    }

    public void testUserIsLoggedInAfterCorrectAuth() throws Exception {
        String usr = "ryan";
        String pass = "pass";

        final ContentValues user = MovieManDbHelper.createUser(usr, pass);
        MovieManDbHelper dbHelper = new MovieManDbHelper(getContext());
        dbHelper.getReadableDatabase().insert(MovieManContract.MovieManUser.TABLE_NAME, null, user);
        try {
            dbHelper.authenticate(usr, "pass");
            PrefUtils.markUserLoggedIn(true, getContext());
            assertTrue(PrefUtils.isUserLoggedIn(getContext()));
        } catch (AuthenticationException e) {
            throw e;
        }
    }
}
