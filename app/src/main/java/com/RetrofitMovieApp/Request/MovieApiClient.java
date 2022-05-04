package com.RetrofitMovieApp.Request;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.RetrofitMovieApp.AppExecutors;
import com.RetrofitMovieApp.Response.MovieSearchResponse;
import com.RetrofitMovieApp.Utils.Credentials;
import com.RetrofitMovieApp.models.MovieModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {

    private static MovieApiClient instance;
    private MutableLiveData<List<MovieModel>> mMovie;
    private  MovieApiClient(){
        mMovie = new MutableLiveData<>();
    }
    public MutableLiveData<List<MovieModel>> getMovies(){
        return mMovie;
    }
    private RetrieveMovieRunnable retrieveMovieRunnable;
    public static MovieApiClient getInstance(){
        if (instance == null){
            instance = new MovieApiClient();
        }
        return  instance;
    }

    public void searchMovieApi(String query, int pageNumber){
        if (retrieveMovieRunnable !=null)
            retrieveMovieRunnable = null;
        retrieveMovieRunnable = new RetrieveMovieRunnable(query,pageNumber);
        final Future mHandler = AppExecutors.getInstance().getmNetworkI().submit(retrieveMovieRunnable);

        AppExecutors.getInstance().getmNetworkI().schedule(new Runnable() {
            @Override
            public void run() {
                // cancelling the retrofit call
                mHandler.cancel(true);
            }
        },3000, TimeUnit.MILLISECONDS);

    }

    private class RetrieveMovieRunnable implements Runnable {
        String query;
        int pageNumber;
        boolean cancelabale;

        public RetrieveMovieRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            cancelabale = false;
        }

        @Override
        public void run() {
            try {
                Response<MovieSearchResponse> response = getMovies(query,pageNumber).execute();
                if (cancelabale){
                    return;
                }
                if (response.code() == 200){
                    ArrayList<MovieModel> modelArrayList = new ArrayList<>(response.body().getMovie());
                    if (pageNumber == 1){
                        mMovie.postValue(modelArrayList);
                    } else {
                        List<MovieModel> currentMovies = mMovie.getValue();
                        currentMovies.addAll(modelArrayList);
                        mMovie.postValue(currentMovies);
                    }

                } else {
                    String error = response.errorBody().string();
                    Log.v("Tag","error: "+error);
                    mMovie.postValue(null);
                }



            } catch (IOException e) {
                e.printStackTrace();
                mMovie.postValue(null);
            }




        }
        private Call<MovieSearchResponse> getMovies(String query, int pageNumber){
            return Servicey.getMovieApi().searchMovie(
                    Credentials.API_KEY,
                    query,
                    String.valueOf(pageNumber));
        }
        private void canelRequest(){
            Log.v("Tag","Cancel movie search request");
        }
    }

}
