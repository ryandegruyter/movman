package com.ryandg.http;

/**
 * Created by Ryan on 10/05/2015.
 */
public interface ResponseCallBack {
    void onSuccess(String response);
    void onRequestError(Exception Error);
}
