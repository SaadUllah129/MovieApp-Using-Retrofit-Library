package com.RetrofitMovieApp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.RetrofitMovieApp.adaptors.MovieViewHolder;

public class MovieModel implements Parcelable {
    private String title, poster_path,release_date,movie_overview, original_language;
    private int id;
    private float vote_average;

    public MovieModel(String title, String poster_path, String release_date, String movie_overview, String original_language, int id, float vote_average) {
        this.title = title;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.movie_overview = movie_overview;
        this.original_language = original_language;
        this.id = id;
        this.vote_average = vote_average;
    }

    protected MovieModel(Parcel in) {
        title = in.readString();
        poster_path = in.readString();
        release_date = in.readString();
        movie_overview = in.readString();
        id = in.readInt();
        vote_average = in.readFloat();
        original_language = in.readString();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    public String getOriginal_language() {
        return original_language;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getMovie_overview() {
        return movie_overview;
    }

    public int getId() {
        return id;
    }

    public float getVote_average() {
        return vote_average;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(poster_path);
        dest.writeString(release_date);
        dest.writeString(movie_overview);
        dest.writeInt(id);
        dest.writeFloat(vote_average);
        dest.writeString(original_language);
    }

    @Override
    public String toString() {
        return "MovieModel{" +
                "title='" + title + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", release_date='" + release_date + '\'' +
                ", movie_overview='" + movie_overview + '\'' +
                ", original_language='" + original_language + '\'' +
                ", id=" + id +
                ", vote_average=" + vote_average +
                '}';
    }
}
