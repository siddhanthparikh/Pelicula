package com.example.android.pelicula;

/**
 * Created by kavit on 21-09-2017.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.android.pelicula.app.AppController;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


import cz.msebera.android.httpclient.Header;

public class LoginPage extends AppCompatActivity {


    private static final String LOG = "LoginPage";
    EditText userEmailEditText;// = (EditText) findViewById(R.id.uEmail);
    String email;// = userEmailEditText.getText().toString();

    EditText userPassEditText;// = (EditText) findViewById(R.id.uPass);
    String password;// = userPassEditText.getText().toString();
    JSONArray user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_page);
        Button b = (Button) findViewById(R.id.signin);
        userEmailEditText = (EditText) findViewById(R.id.uEmail);
        userPassEditText = (EditText) findViewById(R.id.uPass);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = userEmailEditText.getText().toString();

                password = userPassEditText.getText().toString();


                if (email.matches("")) {
                    Toast.makeText(getApplicationContext(), "You have not entered an Email ID", Toast.LENGTH_SHORT).show();
                } else if (password.matches("")) {
                    Toast.makeText(getApplicationContext(), "You have not entered a Password", Toast.LENGTH_SHORT).show();
                } else if (email.matches(password)) {
                    Toast.makeText(getApplicationContext(), "No two fields can have matching values", Toast.LENGTH_SHORT).show();
                } else
                    login2();
            }
        });

/*
                Intent myIntent = new Intent(LoginPage.this, MainActivity.class);
           /* myIntent.putExtra("key", value);*/ //Optional parameters
      /*  LoginPage.this.startActivity(myIntent);*/


    }


    //post
    private void createNewUser() {
        RequestParams params = new RequestParams();
        params.put("email", email);
        params.put("password", password);

        Log.e("email", email);
        Log.e("password", password);

        RestClient.post("user/signup", params, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
            }

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Toast.makeText(LoginPage.this, "login successful", Toast.LENGTH_LONG).show();
                // hideProgressDialog();
                finishAffinity();
                Intent intent = new Intent(LoginPage.this, MainActivity.class);
                intent.putExtra("email", email);
                //  intent.putExtra("PASSWORD", password);
                startActivity(intent);
                // overridePendingTransition(R.anim.enter_from_right, R.anim.do_nothing);
            }


            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject response) {
                if (response == null) {
                    // hideProgressDialog();
                } else {
                    //  Log.e(LOG, Integer.toString(statusCode));
                    //  hideProgressDialog();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString,
                                  Throwable throwable) {
                if (responseString == null) {
                    // hideProgressDialog();
                } else {
                    //  hideProgressDialog();
                    //  Toast.makeText(LoginPage.this, getString(R.string.error_register), Toast.LENGTH_SHORT).show();
                   /* Log.e(LOG, Integer.toString(statusCode));
                    Log.e(LOG, responseString);
                    ErrorDialog dialog = new ErrorDialog(LoginPage.this);
                    dialog.setError(responseString);
                    dialog.show();*/
                }
            }

            @Override
            public void onRetry(int retryNo) {
                super.onRetry(retryNo);
            }
        });
    }


    public void login2(){
        String url = "http://192.168.1.8:5000/validate";
        Map<String,String> params = new HashMap<String,String>();
        params.put("email",email);
        params.put("password",password);

        Log.e(LOG,new JSONObject(params).toString());

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url,new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getInt("count") == 1){
                        Log.e(LOG,"Response = " + response);
                        Intent myIntent = new Intent(LoginPage.this, MainActivity.class);
                        myIntent.putExtra("email", email); //Optional parameters
                        LoginPage.this.startActivity(myIntent);
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

//Login Function
///*
//    public void login() {
//        RequestParams params = new RequestParams();
//        params.setUseJsonStreamer(true);
//        params.put("email", email);
//        params.put("password", password);
//
//        Log.e("email", email);
//        Log.e("password", password);
//
//
//
//        RestClient.post("validate", params, new JsonHttpResponseHandler() {
//            @Override
//            public void onStart() {
//              //  LOG_IN = false;
//               // showProgressDialog(getString(R.string.login_message));
//                Log.e(LOG,"onstart");
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//               // hideProgressDialog();
//                Log.e(LOG, response.toString());
//                try {
//                    if (response.getInt("count")== 1) {
//                        //JSONObject user = response.getJSONObject("count");
//
//                        startActivity(new Intent(LoginPage.this, MainActivity.class));
//                       // else
//
//                        //overridePendingTransition(R.anim.enter_from_right, R.anim.do_nothing);
//                        finish();
//                    } else {
//                      //  Snackbar.make(findViewById(R.id.activity_login), R.string.error, Snackbar.LENGTH_LONG).show();
//                       // Log.e(LOG, response.getString("status"));
//
//                        Toast.makeText(getApplicationContext(), "Not valid", Toast.LENGTH_SHORT).show();
//
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//
//                Toast.makeText(getApplicationContext(), "Not valid 1", Toast.LENGTH_SHORT).show();
//                Log.e(" onFail 1", responseString);
//
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, final JSONObject errorResponse) {
//               // hideProgressDialog();
//               /* if (statusCode == 402)
//                    Snackbar.make(findViewById(R.id.activity_login), R.string.error, Snackbar.LENGTH_LONG).show();
//                else
//                    Snackbar.make(findViewById(R.id.activity_login), R.string.error, Snackbar.LENGTH_LONG).setAction(R.string.details, new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            new ErrorDialog(LoginActivity.this).setError(errorResponse.toString()).setReportButtonVisibility(true).setOnReportButtonClickListener(reportClickListener).show();
//                        }
//                    }).setActionTextColor(Color.WHITE).show();*/
//
//                Toast.makeText(getApplicationContext(), "Not ONFAILURE", Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onRetry(int retryNo) {
//
//            }
//        });
//
//    }*/

}