package com.example.lab2;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class AsyncFlickrJSONDataForList extends AsyncTask<String, Void, JSONObject> {
    private MyAdapter adapter;

    public AsyncFlickrJSONDataForList(MyAdapter adapter) {
        // assign the adapter
        this.adapter = adapter;
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        JSONObject result = null;

        try {
            // retrieve the url
            URL url = new URL(params[0]);

            // connect to the url
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

            try {
                // get data
                InputStream is = new BufferedInputStream(urlConnection.getInputStream());
                String s = readStream(is);

                // transform it into a JSONObject
                result = new JSONObject(s);
            } finally {
                urlConnection.disconnect();
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(JSONObject result) {
        try {
            // retrieve the JSON array of items
            JSONArray items = result.getJSONArray("items");

            // Loop all items
            for(int i = 0; i < items.length(); i++) {
                // retrieve the image url
                String imageUrl = items.getJSONObject(i).getJSONObject("media").getString("m");
                adapter.add(imageUrl);
            }
            // notifying to the adapter of any change
            adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String readStream(InputStream is) {
        try {
            // initialize the output stream
            ByteArrayOutputStream bo = new ByteArrayOutputStream();

            // retrieve the output content
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }

            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }
}