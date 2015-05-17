package com.ryandg.http;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ryan on 10/05/2015.
 *
 *
 */
public class HttpGet {

//    TODO is it necessary to implement mime type check?
    private static final String TAG = HttpGet.class.getSimpleName();
    private static final String CONTENT_ENCODING = "UTF-8";

    private final HttpURLConnection mConnection;

    private ProgressCallBack progressCallBack;

    public HttpGet(HttpURLConnection connection) {
        mConnection = connection;
    }

    public void setProgressCallBack(ProgressCallBack progressCallBack) {
        if (progressCallBack != null) {
            this.progressCallBack = progressCallBack;
        }
    }

    public String fetchResponseBody() throws IOException{
        try {
            mConnection.connect();
            if (isResponseCodeSucces(mConnection.getResponseCode())) {
                return getResponseBodyAsText(mConnection.getInputStream());
            } else {
                throw new HttpResponseException(
                        mConnection.getResponseCode(),
                        "Response not succesfull");
            }
        } catch (IOException exception) {
            throw new HttpResponseException(
                    exception.getMessage(),
                    exception
            );
        } finally {
            mConnection.disconnect();
        }
    }

    private byte[] writeToByteArray(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(getContentLength());

        int read;
        while ((read = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, read);
        }

        return byteArrayOutputStream.toByteArray();
    }

    private byte[] writeToByteArrayWithProgress(ProgressCallBack progressCallBack, InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(getContentLength());

        int read;
        int progress = 0 ;
        while ((read = inputStream.read(buffer)) != -1) {
            progress++;
            progressCallBack.onProgressUpdate(progress);
            byteArrayOutputStream.write(buffer, 0, read);
        }

        return byteArrayOutputStream.toByteArray();
    }

    private String getResponseBodyAsText(InputStream inputStream) throws IOException {
        byte[] responseBody;
        if (progressCallBack != null) {
            responseBody = writeToByteArrayWithProgress(progressCallBack, inputStream);
        } else {
            responseBody = writeToByteArray(inputStream);
        }
        return new String(responseBody, CONTENT_ENCODING);
    }

    private boolean isResponseCodeSucces(int responseCode) {
        return responseCode <= 300 && responseCode >= 200;
    }

    private int getContentLength() {
        if (mConnection.getContentLength() > 0) {
            return mConnection.getContentLength();
        } else {
            return 0;
        }
    }
}
