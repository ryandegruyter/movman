package com.ryandg.movieman.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ryandg.app.TabFragment;

import ryandg.ryandg.movieman.R;

/**
 * Created by Ryan De Gruyter on 20/05/2015.
 */
public class PopularMoviesFragment extends TabFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular_movies, container, false);

        return view;
    }
}
