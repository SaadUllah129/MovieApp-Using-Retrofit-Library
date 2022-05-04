package com.RetrofitMovieApp.Utils;

import com.RetrofitMovieApp.Response.MovieSearchResponse;
import com.RetrofitMovieApp.models.MovieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    // search for a movie
    @GET("3/search/movie")
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") String page
    );

    @GET("3/movie/{movie_id}")
    Call<MovieModel> searchId(
            @Path("movie_id") int movieId,
            @Query("api_key") String api
    );

}
