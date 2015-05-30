package com.ryandg.movieman.validation.rules;

import com.ryandg.movieman.account.AuthenticationException;
import com.ryandg.movieman.validation.ValidationException;

/**
 * Created by Ryan De Gruyter on 28/05/2015.
 */
public abstract class Rule {
    public abstract String getName();

    public abstract void validate(String input) throws ValidationException;
}
