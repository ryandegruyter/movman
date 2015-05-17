package com.ryandg.http;

import java.io.IOException;

/**
 * Created by Ryan on 10/05/2015.
 */
public class HttpResponseException extends IOException {

    private int mStatusCode;

    public HttpResponseException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public HttpResponseException(int statusCode, String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
        mStatusCode = statusCode;
    }

    public HttpResponseException(int statusCode, String message) {
        super(message);
        mStatusCode = statusCode;
    }

    public HttpResponseException(String response) {
        super(response);
    }
}
