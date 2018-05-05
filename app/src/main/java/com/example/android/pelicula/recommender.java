package com.example.android.pelicula;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bhumikapardeshi on 30/09/17.
 */

import static com.example.android.pelicula.R.id.rec_name;
import static com.example.android.pelicula.R.id.rec_genre;



public final class recommender {
    public static final String LOG_TAG = recommender.class.getSimpleName();

    private recommender() {
    }
/*
    public static List<Movie> sendEmail(String urlAdress, String email) throws IOException{

        URL url = createUrl(urlAdress);
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            JSONObject jsonParam = new JSONObject();
            jsonParam.put("email", email);


            Log.i("JSON", jsonParam.toString());
            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
            //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
            os.writeBytes(jsonParam.toString());

            os.flush();
            os.close();


         //   Log.i("STATUS", String.valueOf(conn.getResponseCode()));
           // Log.i("MSG" , conn.getResponseMessage());

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String jsonResponse = null;
        try
        {
            jsonResponse = makehttpRequest(url);
        }catch (IOException e){
            Log.e(LOG_TAG, "Error in making http request", e);
        }
        List<Movie> result = extractEarthquakes(jsonResponse);
        return result;



    }*/

    public static List<Movie> fetchData(String requestUrl){

        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try
        {
            jsonResponse = makehttpRequest(url);
        }catch (IOException e){
            Log.e(LOG_TAG, "Error in making http request", e);
        }
        List<Movie> result = extractEarthquakes(jsonResponse);
        return result;
    }

/*
    public void sendPost() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(urlAdress);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("timestamp", 1488873360);
                    jsonParam.put("uname", message.getUser());
                    jsonParam.put("message", message.getMessage());
                    jsonParam.put("latitude", 0D);
                    jsonParam.put("longitude", 0D);

                    Log.i("JSON", jsonParam.toString());
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                    os.writeBytes(jsonParam.toString());

                    os.flush();
                    os.close();

                    Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                    Log.i("MSG" , conn.getResponseMessage());

                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }*/
    private static URL createUrl(String stringUrl){
        URL url = null;
        try
        {
            url = new URL(stringUrl);
        }catch (MalformedURLException e){
            Log.e(LOG_TAG,"Error in Creating URL",e);
        }
        return url;
    }

    private static String makehttpRequest(URL url) throws IOException{
        String jsonResponse = "";
        if(url == null){
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(15000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

          //  if (urlConnection.getResponseCode() == ) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
         //   } else {
             //   Log.e(LOG_TAG, "Error in connection!! Bad Response ");
          //  }

        }catch (IOException e){
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if(inputStream != null){
                inputStream.close();
            }
        }

        return jsonResponse;

    }
    private static String readFromStream(InputStream inputStream) throws IOException{
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public static List<Movie> extractEarthquakes(String earthquakeJSON){
        if (TextUtils.isEmpty(earthquakeJSON)) {
            return null;
        }
        ArrayList<Movie> earthquakes = new ArrayList<>();
        try {
            JSONArray root = new JSONArray(earthquakeJSON);
           // JSONObject baseJsonResponse = new JSONObject(earthquakeJSON);
           // JSONArray featureArray = baseJsonResponse.getJSONArray("features");

            for (int i = 0; i < root.length(); i++) {
                JSONObject currentEarthquake = root.getJSONObject(i);
              //  JSONObject properties = currentEarthquake.getJSONObject("properties");


           /*     double magnitude = properties.getDouble("mag");
                String location = properties.getString("place");
                long time = properties.getLong("time");
                String Url = properties.getString("url");*/
                String mgenre = currentEarthquake.getString("mgenre");

                String mid = currentEarthquake.getString("mid");

                String mname = currentEarthquake.getString("mname");
                Movie earthquake = new Movie(mid, mname, mgenre);
                earthquakes.add(earthquake);
              //  Log.e(LOG_TAG, "Error in fetching data");

            }

        }catch (JSONException e){
            Log.e(LOG_TAG,"Error in fetching data",e);
        }
        return earthquakes;
    }

}
