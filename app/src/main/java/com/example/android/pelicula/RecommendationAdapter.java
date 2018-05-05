package com.example.android.pelicula;

/**
 * Created by bhumikapardeshi on 23/09/17.
 */
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by USER on 19-09-2017.
 */

public class RecommendationAdapter extends ArrayAdapter<Movie> {

   // ArrayList<Movie> x = new ArrayList<Movie>();

    public RecommendationAdapter(Context context, List<Movie> objects) {
       /* super(context, R.layout.recommendation_row, objects);*/
      //  public EarthquakeAdapter(Context context, List<Earthquake> earthquakes) {
            super(context, 0, objects);
      //  }
        //x.addAll(objects);
    }

    TextView Name, Genre, ID;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;
        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(
                    R.layout.recommendation_row, parent, false);
        }/*
        LayoutInflater listInflator = LayoutInflater.from(getContext());
        View listView = listInflator.inflate(R.layout.recommendation_row, parent, false);*/

        Name = (TextView) listView.findViewById(R.id.rec_name);
        Genre = (TextView) listView.findViewById(R.id.rec_genre);
     /*   ID = (TextView) listView.findViewById(R.id.rec_id);*/

        Movie currentEarthquake = getItem(position);
        TextView txtMagnitude = (TextView) listView.findViewById(R.id.rec_name);
        TextView txtLocation = (TextView) listView.findViewById(R.id.rec_genre);
        txtLocation.setText(currentEarthquake.getGenre());


        // add onclick for the list item

      /*  TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        Date dateobject = new Date(currentEarthquake.getmTimeInMilliseconds());

        String formattedDate = formatDate(dateobject);
        dateView.setText(formattedDate);

        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        String formattedTime = formatTime(dateobject);
        timeView.setText(formattedTime);

        GradientDrawable magnitudeCircle = (GradientDrawable) txtMagnitude.getBackground();
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        String formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitude());
        txtMagnitude.setText(formattedMagnitude);
        magnitudeCircle.setColor(magnitudeColor);
        return listItemView;

      /*  Movie singleMovie = x.get(position);

        Name.setText(singleMovie.getName());


        //ID.setText(singleMovie.getId());
        Genre.setText(singleMovie.getGenre());
*/
        return listView;
    }
}

