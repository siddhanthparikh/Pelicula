package com.example.android.pelicula;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


import android.content.Context;
        import android.net.ConnectivityManager;
        import android.net.NetworkInfo;

        import com.loopj.android.http.AsyncHttpClient;
        import com.loopj.android.http.AsyncHttpResponseHandler;
        import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

/**
 * Created by Samriddha Basu on 5/20/2017.
 */

/*
loginurl = http://www.something.com/api/login
registerurl = http://www.something.com/api/register

YOUR_BASE_URL = http://www.something.com/api/
*/

public class RestClient {
    private static final String BASE_URL = "http://172.16.31.169:5000/";


    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        try {
            client.get(getAbsoluteUrl(url), params, responseHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        try {
            client.post(getAbsoluteUrl(url), params, responseHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        try {
            client.delete(getAbsoluteUrl(url), params, responseHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }


    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

}