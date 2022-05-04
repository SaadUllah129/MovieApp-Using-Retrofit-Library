package com.RetrofitMovieApp.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.RetrofitMovieApp.models.MovieModel;
import com.RetrofitMovieApp.repositories.MovieRepository;

import java.lang.invoke.MutableCallSite;
import java.util.List;

public class MovieListViewModel extends ViewModel {
    MovieRepository movieRepository;

    public MovieListViewModel() {
        movieRepository = MovieRepository.getMovieRepository();
    }

    public MutableLiveData<List<MovieModel>> getmMovie() {
        return movieRepository.getmMovie();
    }

    public void searchMovieApi(String query, int pageNumber){
        movieRepository.searchMovieApi(query, pageNumber);
    }

    public void searchNextPage() {
        movieRepository.searchNextPage();
    }
}
