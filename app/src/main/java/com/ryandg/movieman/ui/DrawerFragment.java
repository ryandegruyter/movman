package com.ryandg.movieman.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.ryandg.PrefUtils;
import com.ryandg.movieman.adapters.DrawerMenuAdapter;

import ryandg.ryandg.movieman.R;

/**
 * Created by Ryan on 19/05/2015.
 */
public class DrawerFragment extends Fragment {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private boolean mUserAwareOfDrawer;
    private boolean mFromSavedInstance;
    private View mContainer;

    private RecyclerView mDrawerMenu;
    private TextView mTitleUserName;

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

        mTitleUserName = (TextView) view.findViewById(R.id.title_user_name);
        mTitleUserName.setText(PrefUtils.getCurrentUser(getActivity()));

        mDrawerMenu = (RecyclerView) view.findViewById(R.id.drawer_links_list);
        mDrawerMenu.setAdapter(new DrawerMenuAdapter(getActivity()));
        mDrawerMenu.setLayoutManager(new LinearLayoutManager(getActivity()));
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
