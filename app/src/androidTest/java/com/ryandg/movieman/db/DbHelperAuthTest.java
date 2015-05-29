package com.ryandg.movieman.db;

import android.content.ContentValues;
import android.test.AndroidTestCase;

import com.ryandg.movieman.account.AuthenticationException;

/**
 * Created by Ryan De Gruyter on 27/05/2015.
 */
public class DbHelperAuthTest extends AndroidTestCase {

    static final String usrName = "ryan";
    static final String usrPass = "fever";

    static final String incorrectUsr = "ryann";
    static final String incorrectPass = "pass";
    private MovieManDbHelper dbHelper;

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        mContext.deleteDatabase(MovieManDbHelper.DB_NAME);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        dbHelper = new MovieManDbHelper(getContext());
        final ContentValues user = MovieManDbHelper.createUser(usrName, usrPass);
        dbHelper.getWritableDatabase().insert(MovieManContract.MovieManUser.TABLE_NAME, null, user);
    }

    public void testAuthUnknownUserNameGivesCorrectMsgException() throws Exception {
        try {
            dbHelper.authenticate(incorrectUsr, incorrectPass);
        } catch (AuthenticationException e) {
            assertEquals(AuthenticationException.MSG_USR_UNKNWN, e.getMessage());
        }
    }

    public void testAuthIncorrectPassWordGivesCorrectMsgException() throws Exception {
        try {
            dbHelper.authenticate(usrName, incorrectPass);
        } catch (AuthenticationException e) {
            assertEquals(AuthenticationException.MSG_INCORRECT_PASSWORD, e.getMessage());
        }
    }

    public void testCorrectCredentials() throws Exception {
        dbHelper.authenticate(usrName, usrPass);
    }
}
