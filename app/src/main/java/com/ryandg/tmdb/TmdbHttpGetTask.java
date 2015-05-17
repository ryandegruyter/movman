package com.ryandg.tmdb;

import android.os.AsyncTask;

import com.ryandg.http.HttpGet;
import com.ryandg.http.HttpResponseException;
import com.ryandg.http.ResponseCallBack;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;

/**
 * Created by Ryan on 10/05/2015.
 *
 *
 */
public class TmdbHttpGetTask extends AsyncTask<Void, Integer, String> {

    private static final String TAG = TmdbHttpGetTask.class.getSimpleName();

    private boolean reQuestIsSuccess;
    private HttpURLConnection mUrlConnection;

    //Activity callbacks. Use WeakReferences to avoid blocking
    // operations causing linked objects to stay in memory
    private WeakReference<ResponseCallBack> mResponseCallBack;

    public TmdbHttpGetTask(HttpURLConnection urlConnection) {
        mUrlConnection = urlConnection;
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            reQuestIsSuccess = true;
            HttpGet getRequest = new HttpGet(mUrlConnection);
            return getRequest.fetchResponseBody();
        } catch (IOException e) {
            reQuestIsSuccess = false;
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String response) {
        if (mResponseCallBack.get() != null) {
            if (reQuestIsSuccess) {
                mResponseCallBack.get().onSuccess(response);
            }else {
                mResponseCallBack.get().onRequestError(new HttpResponseException(response));
            }
        }
    }

    public void setResponseCallBack(ResponseCallBack responseCallBack) {
        this.mResponseCallBack = new WeakReference<>(responseCallBack);
    }
}
