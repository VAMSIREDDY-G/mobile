package com.example.vijaya.earthquakeapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {
    /**
     * Tag for the log messages
     */
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Query the USGS dataset and return a list of {@link Earthquake} objects.
     */
    public static List<Earthquake> fetchEarthquakeData2(String requestUrl) {
        // An empty ArrayList that we can start adding earthquakes to
        List<Earthquake> earthquakes = new ArrayList<>();
        //  URL object to store the url for a given string
        URL url = null;
        // A string to store the response obtained from rest call in the form of string
        String jsonResponse = "";
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //TODO: 1. Create a URL from the requestUrl string and make a GET request to it

            url = new URL(requestUrl);

            //TODO: 2. Read from the Url Connection and store it as a string(jsonResponse)
            URLConnection urlconnect= url.openConnection();
            BufferedReader buffer_in = new BufferedReader(new InputStreamReader(urlconnect.getInputStream()));

            String inputLine;

            while ((inputLine = buffer_in.readLine()) !=  null) {
                stringBuilder.append(inputLine);
            }
            if(buffer_in != null){
                buffer_in.close();
            }
            jsonResponse = stringBuilder.toString();

                /*TODO: 3. Parse the jsonResponse string obtained in step 2 above into JSONObject to extract the values of
                        "mag","place","time","url"for every earth quake and create corresponding Earthquake objects with them
                        Add each earthquake object to the list(earthquakes) and return it.
                */

            JSONObject readerObject = new JSONObject(jsonResponse);


            JSONArray jsonArray = (JSONArray) readerObject.get("features");

            int jsonLen=jsonArray.length();

            for(int count=0;count <jsonLen ; count++){


                JSONObject json = jsonArray.getJSONObject(count).getJSONObject("properties");
                System.out.println(json);

                Earthquake earthquake = new Earthquake((double)json.getDouble("mag"),
                        (String) json.get("place"), (long)json.get("time"), (String) json.get("url"));

                earthquakes.add(earthquake);
            }

        } catch (Exception e) {
            Log.e(LOG_TAG, "Exception:  ", e);
        }
        return earthquakes;
    }
}
