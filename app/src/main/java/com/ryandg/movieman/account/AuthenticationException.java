package com.ryandg.movieman.account;

/**
 * Created by Ryan De Gruyter on 27/05/2015.
 */
public class AuthenticationException extends Exception {
    public static final String MSG_USR_UNKNWN = "Unknown username";
    public static final String MSG_INCORRECT_PASSWORD = "Incorrect password";

    public AuthenticationException() {
        super();
    }

    public AuthenticationException(String detailMessage) {
        super(detailMessage);
    }
}
