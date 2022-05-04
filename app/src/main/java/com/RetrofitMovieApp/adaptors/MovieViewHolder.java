package com.RetrofitMovieApp.adaptors;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.RetrofitMovieApp.R;

import org.jetbrains.annotations.NotNull;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView title, release_date, duration;
    ImageView imageView;
    RatingBar ratingBar;
    onMovieListener onMovieListener;


    public MovieViewHolder(@NonNull @NotNull View itemView, onMovieListener onMovieListener) {
        super(itemView);
        this.onMovieListener = onMovieListener;
        title = itemView.findViewById(R.id.movie_title);
        release_date = itemView.findViewById(R.id.movie_release_date);
        duration = itemView.findViewById(R.id.movie_duration);
        imageView = itemView.findViewById(R.id.movie_image);
        ratingBar = itemView.findViewById(R.id.movie_rating);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onMovieListener.onMovieClick(getAdapterPosition());
    }
}
