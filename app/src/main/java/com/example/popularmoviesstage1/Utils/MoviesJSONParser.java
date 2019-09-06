package com.example.popularmoviesstage1.Utils;


import android.util.Log;

import com.example.popularmoviesstage1.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class MoviesJSONParser {
    private ArrayList<Movie> movies = new ArrayList<>();

    public List<Movie> parseMoviesJSON(String response) {

        JSONObject jSONObject = null;
        try {
            jSONObject = new JSONObject(response);
        } catch (JSONException e) {
            Log.d("ddd", e.toString());
        }
        JSONArray moviesJSONArray = null;
        try {
            moviesJSONArray = jSONObject.getJSONArray("results");
        } catch (JSONException e) {
            Log.d("ddd", e.toString());
        }
        Log.d("ddd", "start parsing");
        for (int i = 0; i < moviesJSONArray.length(); i++) {
            try {
                toMovieObject(moviesJSONArray.getJSONObject(i));
            } catch (JSONException e) {
                Log.d("ddd", e.toString());
            }
        }
        Log.d("ddd", "end parsing");
        return movies;
    }

    private void toMovieObject(JSONObject movieJSONObject) {
        //genre not parssed
        Movie movie = new Movie();
        movie.setPoster_path(movieJSONObject.optString("poster_path"));
        movie.setAdult(movieJSONObject.optBoolean("adult"));
        movie.setId(movieJSONObject.optInt("id"));
        movie.setOverview(movieJSONObject.optString("overview"));
        movie.setRelease_date(movieJSONObject.optString("release_date"));
        movie.setOriginal_title(movieJSONObject.optString("original_title"));
        movie.setOriginal_language(movieJSONObject.optString("original_language"));
        movie.setBackdrop_path(movieJSONObject.optString("backdrop_path"));
        movie.setVote_count(movieJSONObject.optInt("vote_count"));
        movie.setVideo(movieJSONObject.optBoolean("video"));
        movie.setTitle(movieJSONObject.optString("title"));
        movie.setPopularity(movieJSONObject.optDouble("popularity"));
        movie.setVote_average(movieJSONObject.optDouble("vote_average"));
        movies.add(movie);

    }

}
