package com.ryandg.movieman.validation.rules;

import android.content.ContentValues;
import android.content.Context;

import com.ryandg.movieman.account.AuthenticationException;
import com.ryandg.movieman.db.MovieManDbHelper;
import com.ryandg.movieman.validation.ValidationException;

/**
 * Created by Ryan De Gruyter on 28/05/2015.
 */
public class PasswordBelongsToUser extends Rule {
    public static final String RULE_NAME = "rule_correct_password";
    private static final String ERROR_MSG = "Password incorrect";
    private String user;
    private Context context;

    public PasswordBelongsToUser(String user,Context context) {
        this.user = user;
        this.context = context;
    }

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    public void validate(String password) throws ValidationException {
        MovieManDbHelper movieManDbHelper = new MovieManDbHelper(context);
        if (!movieManDbHelper.validPassword(user, password)) {
            throw new ValidationException(ERROR_MSG);
        }
    }
}
