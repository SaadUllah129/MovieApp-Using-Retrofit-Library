package com.RetrofitMovieApp.adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.RetrofitMovieApp.R;
import com.RetrofitMovieApp.models.MovieModel;
import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MovieRecyclerView  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<MovieModel> movies ;
    onMovieListener onMovieListener;

    public MovieRecyclerView(com.RetrofitMovieApp.adaptors.onMovieListener onMovieListener) {
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item,parent,false);
        return  new MovieViewHolder(view,onMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        final MovieModel movie = movies.get(position);
        ((MovieViewHolder)holder).title.setText(movie.getTitle());
        ((MovieViewHolder)holder).release_date.setText(movie.getRelease_date());
        //((MovieViewHolder)holder).duration.setText(movies.get(position).getRunTime());
        ((MovieViewHolder)holder).ratingBar.setRating(movie.getVote_average()/2);
        Glide
            .with(holder.itemView.getContext())
            .load("https://image.tmdb.org/t/p/w500"+movie.getPoster_path())
            .into(((MovieViewHolder)holder).imageView);
        ((MovieViewHolder) holder).duration.setText(movie.getOriginal_language());
    }

    @Override
    public int getItemCount() {
        if (movies != null)
        return movies.size();
        else
            return 0;
    }

    public void setMovies(List<MovieModel> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }
}
