package com.example.android.pelicula;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by bhumikapardeshi on 04/10/17.
 */
public class MovieList implements Parcelable {

    ArrayList<Movie> movie;

    public static final Creator<MovieList> CREATOR = new Creator<MovieList>() {
        @Override
        public MovieList createFromParcel(Parcel source) {
            return new MovieList(source);
        }

        @Override
        public MovieList[] newArray(int size) {
            return new MovieList[size];
        }
    };

    public MovieList(ArrayList<Movie> m){
        this.movie = m;
    }

    protected MovieList(Parcel n){
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
