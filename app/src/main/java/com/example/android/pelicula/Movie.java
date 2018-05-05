package com.example.android.pelicula;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by bhumikapardeshi on 23/09/17.
 */
public class Movie implements Parcelable{
    public String name,genre,id, url;

    public Movie(String name, String genre, String id/*, String url*/) {
        this.name = name;
        this.genre = genre;
        this.id = id;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[0];
        }
    };
            protected Movie(Parcel n){
                this.name = n.readString();
                this.genre = n.readString();
                this.id = n.readString();
            }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getmUrl() {
        return url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.genre);
        dest.writeString(this.id);
    }
}
