package com.ryandg.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Ryan De Gruyter on 20/05/2015.
 */
public abstract class TabFragment extends Fragment{
    private static final String ARG_PAGE_TITLE = "pagetitle";

    public static TabFragment newInstance(TabFragment fragment, String pageTitle) {
        fragment.setTabTitle(pageTitle);
        return fragment;
    }

    public void setTabTitle(String tabTitle) {
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PAGE_TITLE, tabTitle);
        setArguments(bundle);
    }

    public String getTabTitle() {
        return getArguments().getString(ARG_PAGE_TITLE);
    }
}