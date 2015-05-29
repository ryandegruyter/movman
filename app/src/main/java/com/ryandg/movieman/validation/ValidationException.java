package com.ryandg.movieman.validation;

/**
 * Created by Ryan De Gruyter on 28/05/2015.
 */
public class ValidationException extends Exception{
    public ValidationException(String detailMessage) {
        super(detailMessage);
    }
}
