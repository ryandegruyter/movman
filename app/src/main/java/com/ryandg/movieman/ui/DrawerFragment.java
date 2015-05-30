package com.ryandg.movieman.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.ryandg.PrefUtils;

import ryandg.ryandg.movieman.R;

/**
 * Created by Ryan on 19/05/2015.
 */
public class DrawerFragment extends Fragment {

//    pref key om te weten of de
    private static final String KEY_AWARE_OF_DRAWR = "key_aware_of_drawer";
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;


    // check if the user is aware of the drawer and knows of its existence
    private boolean mUserAwareOfDrawer;


    //check if the fragment is started for the first or recreated
    private boolean mFromSavedInstance;
    private View mContainer;


    public DrawerFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserAwareOfDrawer = PrefUtils.isUserAwareOfDrawer(getActivity());

        mFromSavedInstance = savedInstanceState != null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drawer, container, false);

        return view;
    }

    public void init(DrawerLayout drawerLayout, Toolbar toolbar) {
        mContainer = getActivity().findViewById(R.id.drawer_fragment);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (mUserAwareOfDrawer == false) {
                    mUserAwareOfDrawer = true;
                    PrefUtils.setUserAwareOfDrawer(getActivity(), true);
                }
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
                if (!mUserAwareOfDrawer && !mFromSavedInstance) {
                    mDrawerLayout.openDrawer(mContainer);
                }
            }
        });
    }
}
