package com.ryandg.movieman.validation.rules;

import android.content.Context;

import com.ryandg.movieman.data.MovieManDbHelper;
import com.ryandg.movieman.validation.ValidationException;

/**
 * Created by Ryan De Gruyter on 28/05/2015.
 */
public class UserExists extends Rule {
    private static final String RULE_NAME = "rule_usr_exists";
    private static final String ERROR_MSG = "Unknown username";

    private final Context context;

    public UserExists(Context context) {
        this.context = context;
    }

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    public void validate(String input) throws ValidationException {
        final MovieManDbHelper dbHelper = new MovieManDbHelper(context);

        if (!dbHelper.nameExists(input)) {
            throw new ValidationException(ERROR_MSG);
        }
    }
}
