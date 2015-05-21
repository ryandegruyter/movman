package com.ryandg.movieman.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.orhanobut.logger.Logger;
import com.ryandg.app.TabFragment;
import com.ryandg.widget.SlidingTabLayout;

import ryandg.ryandg.movieman.R;

/**
 * Created by Ryan on 19/05/2015.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAB_TITLE_POPULAR = "popular_movies_tab";
    private static final String TAB_TITLE_NEWEST = "newest_movies_tab";
    private Toolbar mToolbar;
    private ViewPager mPager;
    private SlidingTabLayout mTabsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Logger.d("onCreate", savedInstanceState);

        initToolbar();
        initDrawerLayout();
        initPagerAndTabs();
    }

    private void initPagerAndTabs() {
        mPager = (ViewPager) findViewById(R.id.main_pager);
        mPager.setAdapter(new TabFragmentPagerAdapter(getSupportFragmentManager(), getFragments()));
        mTabsLayout = (SlidingTabLayout) findViewById(R.id.main_tabs);
        mTabsLayout.setDistributeEvenly(true);
        mTabsLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.accent);
            }
        });
        mTabsLayout.setViewPager(mPager);
    }

    private void initDrawerLayout() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        DrawerFragment drawerFragment = (DrawerFragment) getSupportFragmentManager().findFragmentById(R.id.drawer_fragment);
        drawerFragment.init(drawerLayout, mToolbar);
    }


    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.main_app_bar);
        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
           case R.id.action_settings:
                //todo go to settings activity
                break;
            case R.id.action_news:
                startActivity(new Intent(this, NewsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public TabFragment[] getFragments() {
        TabFragment popularMoviesFragment = TabFragment.newInstance(new PopularMoviesFragment(), TAB_TITLE_POPULAR);
        TabFragment newMoviesFragment = TabFragment.newInstance(new NewMoviesFragment(), TAB_TITLE_NEWEST);
        return new TabFragment[]{popularMoviesFragment, newMoviesFragment};
    }
}
