package com.ryandg.movieman.validation.rules;

import com.ryandg.movieman.validation.ValidationException;

/**
 * Created by Ryan De Gruyter on 28/05/2015.
 */
public class NotEmpty extends Rule {

    private static final String NAME = "not_empty_rule";
    private static final String ERROR_MSG = "Cant be empty";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void validate(String input) throws ValidationException {
        if (input.equals("")) {
            throw new ValidationException(ERROR_MSG);
        }
    }
}
