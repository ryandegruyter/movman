package com.ryandg.movieman.validation.rules;

import com.ryandg.movieman.validation.ValidationException;

/**
 * Created by Ryan De Gruyter on 28/05/2015.
 */
public class Alphanumeric extends Rule {

    public static final String RULE_NAME = "rule_only_alphanumeric";
    public static final String ERROR_MSG = "Enkel alfa karakters";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    public void validate(String input) throws ValidationException {
        if (!input.matches("^[a-zA-Z0-9]*$")) {
            throw new ValidationException(ERROR_MSG);
        }
    }
}
