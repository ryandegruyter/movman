package com.ryandg.movieman.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.ryandg.DateUtils;
import com.ryandg.movieman.account.AuthenticationException;
import com.ryandg.movieman.data.MovieManContract.MovieManUser.Columns;
import com.ryandg.movieman.pojo.MovieManUser;
import com.ryandg.security.PasswordHash;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static com.ryandg.movieman.account.AuthenticationException.*;

/**
 * Created by Ryan De Gruyter on 26/05/2015.
 */
public class MovieManDbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "movieman.db";
    private static final int DB_VERSION = 1;


    public MovieManDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createUserTable(db);
    }

    public void authenticate(String userName, String passWord) throws AuthenticationException {
        if (!nameExists(userName)) {
            throw new AuthenticationException(MSG_USR_UNKNWN);
        }

        if (!validPassword(userName, passWord)) {
            throw new AuthenticationException(MSG_INCORRECT_PASSWORD);
        }
    }

    public boolean validPassword(String userName, String passWord) {
        final Cursor query = getReadableDatabase().query(
                MovieManContract.MovieManUser.TABLE_NAME,
                new String[]{Columns.COL_USERNAME, Columns.COL_PASSWORD},
                Columns.COL_USERNAME + " = ?",
                new String[]{userName}, null, null, null
        );

        if (query.moveToFirst()) {
            final String usrPassword = query.getString(query.getColumnIndex(Columns.COL_PASSWORD));
            try {
                return PasswordHash.validatePassword(passWord, usrPassword);
            } catch (IllegalArgumentException | NoSuchAlgorithmException | InvalidKeySpecException e) {
                return false;
            }
        }

        return false;
    }

    public boolean nameExists(String userName) {
        final Cursor query = getReadableDatabase().query(
                MovieManContract.MovieManUser.TABLE_NAME,
                new String[]{Columns.COL_USERNAME},
                Columns.COL_USERNAME + " = ?",
                new String[]{userName}, null, null, null
        );

        return query.moveToFirst();
    }

    public MovieManUser getUserByName(String userName) {
        final Cursor query = getReadableDatabase().query(
                MovieManContract.MovieManUser.TABLE_NAME,
                null,
                null, null, null, null, null
        );

        if (query.moveToFirst()) {
            final String qryUserName = query.getString(query.getColumnIndex(Columns.COL_USERNAME));
            final int qryUserId = query.getInt(query.getColumnIndex(BaseColumns._ID));
            return new MovieManUser(qryUserName, qryUserId);
        }
        throw new SQLException("Couldnt find user");
    }

    public static ContentValues createUser(String userName, String passWord) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MovieManContract.MovieManUser.Columns.COL_USERNAME, userName);
        try {
            final String hash = PasswordHash.createHash(passWord);
            contentValues.put(MovieManContract.MovieManUser.Columns.COL_PASSWORD, hash);
        } catch (NoSuchAlgorithmException e) {
        } catch (InvalidKeySpecException e) {
        }

        contentValues.put(MovieManContract.MovieManUser.Columns.COL_DATE_CREATED, DateUtils.getCurrentDateInDbFormat());
        return contentValues;
    }

    private void createUserTable(SQLiteDatabase db) {
        final String SQL_CREATE_USER_TABLE =
                "CREATE TABLE " + MovieManContract.MovieManUser.TABLE_NAME + " (" +
                        BaseColumns._ID + " INTEGER PRIMARY KEY, " +
                        Columns.COL_USERNAME + " TEXT UNIQUE NOT NULL, " +
                        Columns.COL_PASSWORD + " TEXT NOT NULL, " +
                        Columns.COL_DATE_CREATED + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO implement droptable
    }
}
