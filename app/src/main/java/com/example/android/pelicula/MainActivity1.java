package com.example.android.pelicula;

/**
 * Created by bhumikapardeshi on 23/09/17.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MainActivity1 extends AppCompatActivity {
    public static final String LOG_TAG = MainActivity1.class.getName();

    String Email;

    private String USGS_URL ;
           // "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";
//"http://172.20.10.3:5000/getdata";

    private String EMAIL_URL = "http://192.168.1.102:5000/recd";
//    private ListView mListView = (ListView)findViewById(R.id.recList);
    private RecommendationAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        Intent i = getIntent();

        Bundle bundle = getIntent().getExtras();
      //  try{
        Email = bundle.getString("email");
        ArrayList<Movie> movies = bundle.getParcelableArrayList("movie");


        final ListView earthquakeListView = (ListView)findViewById(R.id.recList);
        //mAdapter = new RecommendationAdapter(this, new ArrayList<Movie>());
        mAdapter = new RecommendationAdapter(this, movies);
        earthquakeListView.setAdapter(mAdapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Movie earthquake = mAdapter.getItem(i);
               /* Uri earthquakeUri = Uri.parse(earthquake.getmUrl());
                Intent webIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
                startActivity(webIntent);*/
            }
        });

       /* mListView = (ListView) findViewById(R.id.recList);
        ArrayList<Movie> x = new ArrayList<Movie>();
        x.add(new Movie("Getout", "Horror", "1" ));
        x.add(new Movie("JTCOE", "Adventure", "2" ));
        x.add(new Movie("Annabelle", "Horror", "3" ));
        x.add(new Movie("The Conjuring", "Horror", "4" ));
        x.add(new Movie("Knight and Day", "Thriller", "5" ));
        x.add(new Movie("Bang Bang", "Thriller", "6" ));
        x.add(new Movie("Indiana Jones 1", "Adventure", "7" ));
        x.add(new Movie("Insidious", "Horror", "8" ));
        x.add(new Movie("Spectre", "Thriller", "9" ));
        x.add(new Movie("Up", "Adventure", "10" ));
        x.add(new Movie("Getout", "Horror", "11" ));
        x.add(new Movie("Getout", "Horror", "12" ));
        x.add(new Movie("Getout", "Horror", "13" ));
        x.add(new Movie("Getout", "Horror", "14" ));
        x.add(new Movie("Getout", "Horror", "15" ));
        x.add(new Movie("Getout", "Horror", "16" ));
        x.add(new Movie("Getout", "Horror", "17" ));
        x.add(new Movie("Getout", "Horror", "18" ));
        x.add(new Movie("Getout", "Horror", "19" ));
        mAdapter = new RecommendationAdapter(this, x );
        mListView.setAdapter(mAdapter);
*/

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override

            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                Movie m = (Movie) adapter.getItemAtPosition(position);
                String s1 = m.getId();
                String s2 = m.getName();
                String s3 = m.getGenre();
                // assuming string and if you want to get the value on click of list item
                // do what you intend to do on click of listview row
                Intent myIntent = new Intent(MainActivity1.this, rate.class);
                myIntent.putExtra("email", Email);
                myIntent.putExtra("Mid", s1);
                //Optional parameters
                startActivity(myIntent);
            }
        });





       /* Add your code here somewhere in this file to load the movies and set

         it to the adapter.
         */

        QuakeAsyncTask task = new QuakeAsyncTask();
      //  task.execute(USGS_URL);

    }
    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(MainActivity1.this, MainActivity.class);
        //Optional parameters
        startActivity(myIntent);
    }


    private class QuakeAsyncTask extends AsyncTask<String,Void,List<Movie>> {

        private ProgressDialog progressDialog;

        @Override
        protected List<Movie> doInBackground(String... strings) {

          /*  List<Movie> result = null;
            try {
                result = recommender.sendEmail(EMAIL_URL, "rutvij@gmail.com");
            } catch (IOException e) {
                e.printStackTrace();
            }*/

            List<Movie> result = recommender.fetchData(USGS_URL);
            return result;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(MainActivity1.this);
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            super.onPreExecute();
        }
        @Override
        protected void onPostExecute(List<Movie> data) {
            mAdapter.clear();
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
                if(data != null)
                Toast.makeText(getApplicationContext(),"showing process",Toast.LENGTH_SHORT).show();
            }

            if (data != null && !data.isEmpty()){
                mAdapter.addAll(data);

                Toast.makeText(getApplicationContext(),"view the data",Toast.LENGTH_LONG).show();
            }
        }
    }

}
