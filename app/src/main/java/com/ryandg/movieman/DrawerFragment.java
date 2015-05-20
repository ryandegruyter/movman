package com.ryandg.movieman;

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
 * We gebruiken een fragment voor de drawer navigatie omdat de Material Design spec
 * vraagt om de Drawer heel de hoogte in te nemen en over de Actionbar te tonen.
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


    public DrawerFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserAwareOfDrawer = PrefUtils.isUserAwareOfDrawer(getActivity());

        if (savedInstanceState != null) {
            mFromSavedInstance = true;
        } else {
            mFromSavedInstance = false;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drawer, container, false);

        return view;
    }

    public void init(DrawerLayout drawerLayout, Toolbar toolbar) {
        
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

        if (!mUserAwareOfDrawer && mFromSavedInstance) {
            View drawerView = getActivity().findViewById(R.id.drawer_fragment);
            mDrawerLayout.openDrawer(drawerView);
        }

        Logger.d("init drawer Aware: %s, FromSaveInstance: %s", mUserAwareOfDrawer, mFromSavedInstance);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }
}
