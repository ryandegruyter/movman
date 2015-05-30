package com.ryandg.movieman.adapters.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ryandg.ryandg.movieman.R;

/**
 * Created by Ryan on 30/05/2015.
 */
public class DrawerMenuRow extends RecyclerView.ViewHolder implements View.OnClickListener{

    private ImageView icon;
    private TextView link;

    public DrawerMenuRow(View row) {
        super(row);

        icon = (ImageView) row.findViewById(R.id.ic_drawer_menu_row);
        link = (TextView) row.findViewById(R.id.drawer_menu_row_text);

        row.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    public void bind(int stringResId) {
        final String menuItem = link.getContext().getString(stringResId);
        link.setText(menuItem);
        setIcon(stringResId);
    }

    private void setIcon(int stringResId) {
        switch (stringResId) {
            case R.string.recent_movies:
                icon.setImageResource(R.drawable.ic_film);
                break;
            case R.string.profile:
                icon.setImageResource(R.drawable.ic_profile);
                break;
            case R.string.search_movies:
                icon.setImageResource(R.drawable.ic_search);
                break;
        }
    }
}
