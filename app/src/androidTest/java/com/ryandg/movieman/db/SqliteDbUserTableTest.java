package com.ryandg.movieman.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.test.AndroidTestCase;

import com.ryandg.DateUtils;
import com.ryandg.security.PasswordHash;

import org.junit.runner.RunWith;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static com.ryandg.movieman.data.MovieManDbHelper.createUser;

/**
 * Created by Ryan De Gruyter on 26/05/2015.
 */
public class SqliteDbUserTableTest extends AndroidTestCase {
    SQLiteOpenHelper mDbHelper;

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        getContext().deleteDatabase(MovieManDbHelper.DB_NAME);
    }
    @Override
    public void setUp() throws Exception {
        super.setUp();
        mDbHelper = new MovieManDbHelper(getContext());
    }

    public void testDatabaseNotNull() throws Exception {
        assertNotNull(mDbHelper.getReadableDatabase());
    }

    public void testInsertUser() {
        String user = "Ryan";
        String passWord = "ryan";
        ContentValues movieManUser = createUser(user, passWord);
        final long insertId = mDbHelper.getWritableDatabase().insert(MovieManContract.MovieManUser.TABLE_NAME, null, movieManUser);
        assertFalse(insertId == -1);
    }

    public void testOnlyUniqueNamesAllowed() {
        final ContentValues user = createUser("Ryan", "password");
        final ContentValues userNotAllowed = createUser("Ryan", "doulbe");

        mDbHelper.getWritableDatabase().insert(MovieManContract.MovieManUser.TABLE_NAME, null, user);
        final long insert = mDbHelper.getWritableDatabase().insert(MovieManContract.MovieManUser.TABLE_NAME, null, userNotAllowed);
        assertTrue(insert == -1);
    }

    public void testPasswordIsHashedBeforeSavedToDatabase() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String username = "Ryan";
        String userPass = "pass";
        final ContentValues user = createUser(username, userPass);
        final long insertId = mDbHelper.getWritableDatabase().insert(MovieManContract.MovieManUser.TABLE_NAME, null, user);
        assertTrue(insertId != -1);
        final String password = qryPassWord(username);
        assertFalse("Passwoord mag niet opgeslagen worden in database, enkel hasj van passwoord.",password.equals(userPass));
        assertTrue(PasswordHash.validatePassword(userPass, password));
    }

    public void testUserDateCreatedIsCorrect() {
        String currentDate = DateUtils.getCurrentDateInDbFormat();

        mDbHelper.getWritableDatabase().insert(MovieManContract.MovieManUser.TABLE_NAME, null, createUser("Donald", "duck"));

        final Cursor query = mDbHelper.getReadableDatabase().query(MovieManContract.MovieManUser.TABLE_NAME,
                new String[]{MovieManContract.MovieManUser.Columns.COL_DATE_CREATED},
                MovieManContract.MovieManUser.Columns.COL_USERNAME + " = ?",
                new String[]{"Donald"},
                null, null, null);

        query.moveToFirst();
        final String dateCreated = query.getString(query.getColumnIndex(MovieManContract.MovieManUser.Columns.COL_DATE_CREATED));

        assertEquals(currentDate, dateCreated);
    }

    private String qryPassWord(String username) {
        final Cursor query = mDbHelper.getReadableDatabase().query(MovieManContract.MovieManUser.TABLE_NAME,
                new String[]{MovieManContract.MovieManUser.Columns.COL_PASSWORD},
                MovieManContract.MovieManUser.Columns.COL_USERNAME + " = ?",
                new String[]{username},
                null, null, null);

        query.moveToFirst();
        final String password = query.getString(query.getColumnIndex(MovieManContract.MovieManUser.Columns.COL_PASSWORD));
        return password;
    }
}
