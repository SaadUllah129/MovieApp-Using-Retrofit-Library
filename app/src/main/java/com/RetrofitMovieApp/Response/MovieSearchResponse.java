package com.RetrofitMovieApp.Response;

import com.RetrofitMovieApp.models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieSearchResponse {
    @SerializedName("total_results")
    @Expose()
    private int total_count;

    @SerializedName("results")
    @Expose()
    private ArrayList<MovieModel> movie;

    public int getTotal_count(){
        return total_count;
    }
    public ArrayList<MovieModel> getMovie(){
        return movie;
    }

    @Override
    public String toString() {
        return "MovieSearchResponse{" +
                "total_count=" + total_count +
                ", movie=" + movie +
                '}';
    }
}
