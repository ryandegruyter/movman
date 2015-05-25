package com.ryandg.movieman.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ryandg.tmdb.TmdbMovie;

import java.util.ArrayList;

import ryandg.ryandg.movieman.R;

/**
 * Created by Ryan on 21/05/2015.
 */
public class AdapterPopularMovies extends RecyclerView.Adapter<AdapterPopularMovies.ViewHolderPopularMovies> {

    private LayoutInflater layoutInflater;
    private ArrayList<TmdbMovie> mMovieList;

    public AdapterPopularMovies(Context context) {
        layoutInflater = layoutInflater.from(context);
    }

    public void setMovieList(ArrayList<TmdbMovie> movieList) {
        mMovieList = movieList;
        notifyItemRangeChanged(0, movieList.size());
    }

    @Override
    public ViewHolderPopularMovies onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.popular_movie_list_item, parent, false);

        final ViewHolderPopularMovies viewHolderPopularMovies = new ViewHolderPopularMovies(view);
        return viewHolderPopularMovies;
    }

    @Override
    public void onBindViewHolder(ViewHolderPopularMovies holder, int position) {
        TmdbMovie currentMovie = mMovieList.get(position);
        holder.movieTitle.setText(currentMovie.getTitle());
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    static class ViewHolderPopularMovies extends RecyclerView.ViewHolder {

        ImageView movieThumb;
        TextView movieTitle;
        TextView movieReleaseDate;
        RatingBar movieViewerScore;

        public ViewHolderPopularMovies(View itemView) {
            super(itemView);
            movieThumb = (ImageView) itemView.findViewById(R.id.movieThumb);
            movieTitle = (TextView) itemView.findViewById(R.id.movieTitle);
            movieReleaseDate = (TextView) itemView.findViewById(R.id.movieReleaseDate);
            movieViewerScore = (RatingBar) itemView.findViewById(R.id.movieViewerScore);
        }
    }
}
