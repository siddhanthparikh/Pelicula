package com.example.android.pelicula;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.pelicula.app.AppController;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * Created by kavit on 22-09-2017.
 */

public class RegPage extends AppCompatActivity {
   // String Email;


    EditText userNameEditText;
    String username;//= userNameEditText.getText().toString();

    EditText userEmailEditText;// = (EditText) findViewById(R.id.uEmail);
    String email;// = userEmailEditText.getText().toString();

    EditText userPassEditText;// = (EditText) findViewById(R.id.uPass);
    String password;// = userPassEditText.getText().toString();

    EditText userPassrEditText;// = (EditText) findViewById(R.id.uPassr);
    String rpassword;// = userPassrEditText.getText().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        Button b = (Button) findViewById(R.id.regButton);


        userNameEditText = (EditText) findViewById(R.id.uName);
        userEmailEditText = (EditText) findViewById(R.id.uEmail);
        userPassEditText = (EditText) findViewById(R.id.uPass);
        userPassrEditText = (EditText) findViewById(R.id.uPassr);

        assert b != null;
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = userNameEditText.getText().toString();
                email = userEmailEditText.getText().toString();
                password = userPassEditText.getText().toString();
                rpassword = userPassrEditText.getText().toString();


                if (username.matches("")) {
                    Toast.makeText(getApplicationContext(), "You have not entered a Name", Toast.LENGTH_SHORT).show();
                } else if (email.matches("")) {
                    Toast.makeText(getApplicationContext(), "You have not entered an Email ID", Toast.LENGTH_SHORT).show();
                } else if (password.matches("")) {
                    Toast.makeText(getApplicationContext(), "You have not entered a Password", Toast.LENGTH_SHORT).show();
                } else if (rpassword.matches("")) {
                    Toast.makeText(getApplicationContext(), "Re-type your Password!", Toast.LENGTH_SHORT).show();
                } else if (email.matches(username) || email.matches(password) || username.matches(password)) {
                    Toast.makeText(getApplicationContext(), "No two fields can have matching values", Toast.LENGTH_SHORT).show();
                } else if (!password.matches(rpassword)) {
                    Toast.makeText(getApplicationContext(), "Passwords Don't Match", Toast.LENGTH_SHORT).show();
                } else {

                    register();

                }
            }

        });


    }

    //register
    private void register() {

        String url = "http://192.168.1.8:5000/register";
        Map<String,String> params = new HashMap<String,String>();
        params.put("name", username);
        params.put("email",email);
        params.put("password", password);


        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url,new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getInt("count") == 1){
                        Log.e("in register","Response = " + response);
                        Intent myIntent = new Intent(RegPage.this, MainActivity.class);
                        myIntent.putExtra("email", email); //Optional parameters
                        startActivity(myIntent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();}
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
