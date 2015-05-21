package com.ryandg.movieman.ui;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ryandg.app.TabFragment;
import com.ryandg.movieman.MovieManApplication;
import com.ryandg.net.ImageCache;
import com.ryandg.net.VolleySingleton;
import com.ryandg.tmdb.TmdbKey;
import com.ryandg.tmdb.TmdbUtils;

import org.json.JSONObject;

import java.util.logging.Logger;

import ryandg.ryandg.movieman.R;

/**
 * Created by Ryan De Gruyter on 20/05/2015.
 */
public class PopularMoviesFragment extends TabFragment {

    private VolleySingleton volleySingleton;
    private ImageCache imageCache;
    private RequestQueue requestQueue;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        volleySingleton = VolleySingleton.getInstance(getActivity());
        requestQueue = volleySingleton.getRequestQueue();

        final String url = String.format(TmdbUtils.POPULAR_MOVIES, TmdbKey.API);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        com.orhanobut.logger.Logger.json(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        com.orhanobut.logger.Logger.d(error.getMessage());
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
        requestQueue.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular_movies, container, false);

        return view;
    }
}
