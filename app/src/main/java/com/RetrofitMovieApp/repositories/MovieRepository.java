package com.RetrofitMovieApp.repositories;

import androidx.lifecycle.MutableLiveData;
import com.RetrofitMovieApp.Request.MovieApiClient;
import com.RetrofitMovieApp.models.MovieModel;
import java.util.List;

public class MovieRepository {

    private static MovieRepository instance;
    private String mQuery;
    private int mPageNumber;
    //this class is used for view model
    MovieApiClient movieApiClient;
    private  MovieRepository(){
        movieApiClient = MovieApiClient.getInstance();
    }

    public static MovieRepository getMovieRepository(){
        if (instance == null){
            instance = new MovieRepository();
        }
        return instance;
    }
    public void searchMovieApi(String query, int pageNumber){
        mQuery = query;
        mPageNumber = pageNumber;
        movieApiClient.searchMovieApi(query,pageNumber);
    }

    public MutableLiveData<List<MovieModel>> getmMovie() {
        return movieApiClient.getMovies();
    }

    public void searchNextPage() {
        movieApiClient.searchMovieApi(mQuery,mPageNumber+1);
    }
}
