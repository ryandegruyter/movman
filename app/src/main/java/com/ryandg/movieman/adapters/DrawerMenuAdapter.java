package com.ryandg.movieman.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ryandg.movieman.adapters.viewholder.DrawerMenuRow;

import ryandg.ryandg.movieman.R;

/**
 * Created by Ryan on 30/05/2015.
 */
public class DrawerMenuAdapter extends RecyclerView.Adapter<DrawerMenuRow> {
    private final Context context;
    private int[] menuItems;

    public DrawerMenuAdapter(Context context) {
        this.context = context;
        menuItems = new int[]{R.string.recent_movies, R.string.search_movies, R.string.profile};
    }

    @Override
    public DrawerMenuRow onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DrawerMenuRow(LayoutInflater.from(context).inflate(R.layout.drawer_menu_row, parent, false));
    }

    @Override
    public void onBindViewHolder(DrawerMenuRow holder, int position) {
        holder.bind(menuItems[position]);
    }

    @Override
    public int getItemCount() {
        return menuItems.length;
    }
}
