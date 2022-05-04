package com.RetrofitMovieApp.Request;

import com.RetrofitMovieApp.Utils.Credentials;
import com.RetrofitMovieApp.Utils.MovieApi;
import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Servicey {
    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static MovieApi movie = retrofit.create(MovieApi.class);

    public static MovieApi getMovieApi (){
        return  movie;
    }

}
