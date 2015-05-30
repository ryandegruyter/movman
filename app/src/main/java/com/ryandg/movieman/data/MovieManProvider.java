package com.ryandg.movieman.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

import java.sql.SQLException;

/**
 * Created by Ryan on 30/05/2015.
 */
public class MovieManProvider extends ContentProvider {

    private MovieManDbHelper movieManDbHelper;
    private static final UriMatcher URI_MATCHER;

    private static final int USER = 100;
    private static final int USER_ID = 101;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(MovieManContract.CONTENT_AUTHORITY, MovieManContract.MovieManUser.PATH_USER, USER);
        URI_MATCHER.addURI(MovieManContract.CONTENT_AUTHORITY, MovieManContract.MovieManUser.PATH_USER + "/#", USER_ID);
    }

    @Override
    public boolean onCreate() {
        movieManDbHelper = new MovieManDbHelper(getContext());
        return movieManDbHelper != null;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor returnCursor;

        switch (URI_MATCHER.match(uri)) {
            case USER:
                returnCursor = movieManDbHelper.getReadableDatabase().query(
                        MovieManContract.MovieManUser.TABLE_NAME,
                        projection, selection, selectionArgs, null, null, sortOrder
                );
                break;
            case USER_ID:
                returnCursor = movieManDbHelper.getReadableDatabase().query(
                        MovieManContract.MovieManUser.TABLE_NAME,
                        projection,
                        BaseColumns._ID + " = '" + ContentUris.parseId(uri) + "'",
                        selectionArgs, null, null, sortOrder
                );
                break;

            default:
                throw new UnsupportedOperationException("Unknown Uri: " + uri);
        }

        returnCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return returnCursor;
    }

    @Override
    public String getType(Uri uri) {
        final int match = URI_MATCHER.match(uri);
        switch (match) {
            case USER:
                return MovieManContract.MovieManUser.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown Uri: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final int match = URI_MATCHER.match(uri);
        Uri returnUri;
        switch (match) {
            case USER:
                final long rowId = movieManDbHelper.getWritableDatabase().insert(MovieManContract.MovieManUser.TABLE_NAME, null, values);
                if (rowId > 0) {
                    returnUri = ContentUris.withAppendedId(MovieManContract.MovieManUser.CONTENT_URI, rowId);
                } else {
                    throw new android.database.SQLException("Failed to insert row " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
