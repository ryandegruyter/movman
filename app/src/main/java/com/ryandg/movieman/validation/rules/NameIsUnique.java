package com.ryandg.movieman.validation.rules;

import android.content.Context;

import com.ryandg.movieman.data.MovieManDbHelper;
import com.ryandg.movieman.validation.ValidationException;

/**
 * Created by Ryan De Gruyter on 29/05/2015.
 */
public class NameIsUnique extends Rule {
    private static final String RULE_NAME = "rule_unique_name";
    private static final String ERROR_MSG = "Name already exists, choose another one";
    private Context context;

    public NameIsUnique(Context context) {
        this.context = context;
    }

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    public void validate(String input) throws ValidationException {
        MovieManDbHelper movieManDbHelper = new MovieManDbHelper(context);
        if (movieManDbHelper.nameExists(input)) {
            throw new ValidationException(ERROR_MSG);
        }
    }
}
