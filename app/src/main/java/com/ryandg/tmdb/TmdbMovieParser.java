package com.ryandg.tmdb;

import android.util.JsonReader;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by Ryan on 09/05/2015.
 * Reads Tmdb json objects
 */
public class TmdbMovieParser {
    private ArrayList<TmdbMovie> movies;

    public TmdbMovieParser() {
    }

    public void parse(String tmdbJsonResponse) throws IOException {
        final StringReader stringReader = new StringReader(tmdbJsonResponse);
        final JsonReader jsonReader = new JsonReader(stringReader);
        this.movies = new ArrayList<>();

        jsonReader.beginObject();
        readPage(jsonReader);
        jsonReader.endObject();
    }

    private void readPage(JsonReader jsonReader) throws IOException {
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            if (name.equals("results")) {
                readMovies(jsonReader);
            }else{
                jsonReader.skipValue();
            }
        }
    }

    private void readMovies(JsonReader jsonReader) throws IOException {
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            movies.add(readMovie(jsonReader));
        }
        jsonReader.endArray();
    }

    private TmdbMovie readMovie(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        TmdbMovie tmdbMovie = new TmdbMovie();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            setMovieProperty(name, tmdbMovie, jsonReader);
        }
        jsonReader.endObject();
        return tmdbMovie;
    }

    private void setMovieProperty(String name, TmdbMovie tmdbMovie, JsonReader jsonReader) throws IOException {
        switch (name) {
            case TmdbMovieColumns.IS_ADULT:
                tmdbMovie.setIsAdult(jsonReader.nextBoolean());
                break;
            case TmdbMovieColumns.BACKDROP_PATH:
                tmdbMovie.setBackDropPath(jsonReader.nextString());
                break;
            case TmdbMovieColumns.ID:
                tmdbMovie.setId(jsonReader.nextInt());
                break;
            case TmdbMovieColumns.ORIGINAL_TITLE:
                tmdbMovie.setOriginalTitle(jsonReader.nextString());
                break;
            case TmdbMovieColumns.POPULARITY:
                tmdbMovie.setPopularity(jsonReader.nextDouble());
                break;
            case TmdbMovieColumns.TITLE:
                tmdbMovie.setTitle(jsonReader.nextString());
                break;
            case TmdbMovieColumns.VOTE_AVERAGE:
                tmdbMovie.setVoteAverage(jsonReader.nextDouble());
                break;
            case TmdbMovieColumns.VOTE_COUNT:
                tmdbMovie.setVoteCount(jsonReader.nextInt());
                break;
            case TmdbMovieColumns.POSTER_PATH:
                tmdbMovie.setPosterPath(jsonReader.nextString());
                break;
            case TmdbMovieColumns.RELEASE_DATE:
                tmdbMovie.setReleaseDate(jsonReader.nextString());
                break;
            default:
                jsonReader.skipValue();
                break;
        }
    }

    public ArrayList<TmdbMovie> getMovies() throws IOException {
        if (movies == null || movies.size() == 0) {
            throw new IOException("Couldnt parse any movies");
        }
        return movies;
    }
}
