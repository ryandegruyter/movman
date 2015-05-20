package com.ryandg.movieman.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ryandg.app.TabFragment;

/**
 * Created by Ryan De Gruyter on 20/05/2015.
 */
public class TabFragmentPagerAdapter extends FragmentPagerAdapter {

    private TabFragment[] mFragments;

    public TabFragmentPagerAdapter(FragmentManager fm, TabFragment[] fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments[position];
    }

    @Override
    public int getCount() {
        return mFragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments[position].getTabTitle();
    }
}
