package com.example.android.pelicula;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.android.pelicula.MainActivity1;
import com.example.android.pelicula.R;
import com.example.android.pelicula.app.AppController;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * Created by kavit on 22-09-2017.
 */

public class rate extends AppCompatActivity {
    double quantity = 0;
    String mid;
    String rating;
    String Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);


        Intent i = getIntent();

        Bundle bundle = getIntent().getExtras();
        Email = bundle.getString("email");
        mid = bundle.getString("Mid");
    }

    public void save(View view) {

        Toast.makeText(getApplicationContext(),"Rating Saved",Toast.LENGTH_LONG).show();
        rateMovie();
        Intent myIntent = new Intent(rate.this, MainActivity1.class);
           /* myIntent.putExtra("key", value);*/ //Optional parameters
        startActivity(myIntent);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(double number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayRating(double number) {
        TextView priceTextView = (TextView) findViewById(R.id.quantity_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {

            quantity = quantity + 0.5;
        if (quantity>=0 && quantity<=5) {
            display(quantity);
        }
        if(quantity<0 || quantity >5)
        {
            Toast.makeText(getApplicationContext(), "Invalid Rating", Toast.LENGTH_SHORT).show();
            quantity=0;
            display(quantity);
        }
    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {
        quantity = quantity - 0.5;
        if (quantity>=0 && quantity<=5) {
            display(quantity);
        }
        if(quantity<0 || quantity >5)
        {
            Toast.makeText(getApplicationContext(), "Invalid Rating", Toast.LENGTH_SHORT).show();
            quantity=0;
            display(quantity);
        }

    }

    //post
    private void rateMovie() {

        String url = "http://192.168.1.8:5000/savedata";
        Map<String,String> params = new HashMap<String,String>();
        params.put("email",Email);
        params.put("mid",mid);
        rating = Double.toString(quantity);
        params.put("rating", rating);

      //  Log.e(LOG,new JSONObject(params).toString());

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url,new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getInt("count") == 1){
                        Log.e("yes","Response = " + response);
                        Intent myIntent = new Intent(rate.this, MainActivity1.class);
                        myIntent.putExtra("email", Email); //Optional parameters
                        startActivity(myIntent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Invalid studd", Toast.LENGTH_SHORT).show();}
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
