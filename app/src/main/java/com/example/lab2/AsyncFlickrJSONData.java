package com.example.lab2;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class AsyncFlickrJSONData extends AsyncTask<String, Void, JSONObject> {
    private AsyncBitmapDownloader asyncBmp;

    public AsyncFlickrJSONData(AsyncBitmapDownloader asyncBmp) {
        // assign the async bitmap downloader
        this.asyncBmp = asyncBmp;
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
            String imageUrl = result.getJSONArray("items").getJSONObject(0).getJSONObject("media").getString("m");
            // show the image URL in LOG
            Log.i("JFL", imageUrl);
            // execute the image downlaoder
            asyncBmp.execute(imageUrl);
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