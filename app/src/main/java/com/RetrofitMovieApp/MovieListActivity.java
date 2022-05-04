package com.RetrofitMovieApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.widget.Toolbar;

import com.RetrofitMovieApp.Request.Servicey;
import com.RetrofitMovieApp.Response.MovieSearchResponse;
import com.RetrofitMovieApp.Utils.Credentials;
import com.RetrofitMovieApp.Utils.MovieApi;
import com.RetrofitMovieApp.adaptors.MovieRecyclerView;
import com.RetrofitMovieApp.adaptors.onMovieListener;
import com.RetrofitMovieApp.models.MovieModel;
import com.RetrofitMovieApp.viewmodels.MovieListViewModel;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListActivity extends AppCompatActivity implements onMovieListener {
    RecyclerView recyclerView;
    MovieRecyclerView adapter;
    private MovieListViewModel movieListViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        searchtoolBar();
        recyclerView = findViewById(R.id.movie_recycler_view);
        ConfigRecyclerView();
        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);
        ObserveAnyChange();
    }

    private void searchtoolBar() {
        final SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                movieListViewModel.searchMovieApi(
                        query,
                        1
                );
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }


    private void ObserveAnyChange(){
        movieListViewModel.getmMovie().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                // here we will give data to our UIs when new data arrived...
                if (movieModels != null){
                    for (MovieModel movie: movieModels){
                        Log.v("Tag","onChanged: "+movie.getTitle());
                    }
                    adapter.setMovies(movieModels);
                }
            }
        });
    }
    //
    private void ConfigRecyclerView(){
        adapter = new MovieRecyclerView(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                if (!recyclerView.canScrollVertically(1)){
                    movieListViewModel.searchNextPage();
                }
            }
        });
    }

    private void GetRetrofitResponse() {
        MovieApi movieApi = Servicey.getMovieApi();

        Call<MovieSearchResponse> responseCall = movieApi
        .searchMovie(
                Credentials.API_KEY,
                "jack reacher",
                "1"
        );

        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if (response.code() == 200){
                    Log.v("Tag", "The Response is : "+ response.body().toString());
                    ArrayList<MovieModel> movies =  new ArrayList<>(response.body().getMovie());
                    for(MovieModel movie: movies){
                        Log.v("Tag", "The release date is : "+movie.getRelease_date());
                    }

                } else {
                    try {
                        Log.v("Tag", "Error: "+response.errorBody().string());
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {

            }
        });
    }

    private void GetMovieNameById(){
        MovieApi movieApi = Servicey.getMovieApi();
        Call<MovieModel> modelCall = movieApi.searchId(
                550,
                Credentials.API_KEY
        );

        modelCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if (response.code() == 200){
                    MovieModel movieModel = response.body();
                    Log.v("Tag"," Title: "+movieModel.getTitle());
                } else {
                    try {
                        Log.v("Tag", "Error: "+ response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {

            }
        });
    }

    @Override
    public void onMovieClick(int position) {

    }

    @Override
    public void onCategoryClick(String category) {

    }

}