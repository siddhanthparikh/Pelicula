package com.example.android.pelicula;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.android.pelicula.app.AppController;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class  sort extends AppCompatActivity {

    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);


        Intent i = getIntent();
        email = i.getStringExtra(email);

        Button b = (Button) findViewById(R.id.adv);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sort("Adventure");
               /* Intent myIntent = new Intent(sort.this, MainActivity1.class);
                ArrayList<Movie> m = new ArrayList<Movie>();
                myIntent.putExtra("movie", m);
           /* myIntent.putExtra("key", value); //Optional parameters
                startActivity(myIntent);*/
            }
        });

        Button b1 = (Button) findViewById(R.id.com);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(sort.this, MainActivity1.class);
           /* myIntent.putExtra("key", value);*/ //Optional parameters
                startActivity(myIntent);
            }
        });

        Button b2 = (Button) findViewById(R.id.hor);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(sort.this, MainActivity1.class);
           /* myIntent.putExtra("key", value);*/ //Optional parameters
                startActivity(myIntent);
            }
        });

        Button b3 = (Button) findViewById(R.id.rom);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(sort.this, MainActivity1.class);
           /* myIntent.putExtra("key", value);*/ //Optional parameters
                startActivity(myIntent);
            }
        });

        Button b4 = (Button) findViewById(R.id.thril);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(sort.this, MainActivity1.class);
           /* myIntent.putExtra("key", value);*/ //Optional parameters
                startActivity(myIntent);
            }
        });
    }

    public void sort(String genre) {

        String url = "http://192.168.1.8:5000/getgenre";
        Map<String,String> params = new HashMap<String,String>();
        params.put("genre", genre);
        final ArrayList<Movie> movie = new ArrayList<Movie>();
        //Log.e(LOG, new JSONObject(params).toString());

        //Map<String,String> params = new HashMap<String, String>();


        Log.e("yes response",new JSONObject(params).toString());

        final JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url,new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("list");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String mgenre = jsonObject.getString("genre");

                        String mid = jsonObject.getString("mid");

                        String mname = jsonObject.getString("mname");
                        Movie mi = new Movie(mid, mname, mgenre);
                        movie.add(mi);

                    }

                    Toast.makeText(getApplicationContext(), "done parsing", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(sort.this, MainActivity1.class);
                    i.putExtra("movie", movie);
                    i.putExtra("email", email);
                    startActivity(i);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(jsonRequest,"tag");



    }
}
