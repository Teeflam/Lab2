package com.example.lab2;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;

import java.util.Vector;

public class MyAdapter extends BaseAdapter {
    private Vector<String> imageUrls;

    public MyAdapter() {
        // initialize the vector
        imageUrls = new Vector<>();
    }

    // add url in the vector
    public void add(String url) {
        // add the url
        imageUrls.add(url);

        // log the adding
        Log.i("JFL", "Adding to adapter url: " + url);
    }

    @Override
    public int getCount() {
        return imageUrls.size();
    }

    @Override
    public Object getItem(int position) {
        return imageUrls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // instantiate a request queue
        RequestQueue queue = MySingleton.getInstance(parent.getContext()).getRequestQueue();

        // inflate the layout for each url
        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.bitmaplayout, parent, false);
        }

        // retrieve the image url
        String imageUrl = (String) getItem(position);

        // retrieve the image view container
        ImageView image = convertView.findViewById(R.id.image_bml);

        // create a response listener
        Response.Listener<Bitmap> resp_listener = bmp -> {
            // set the image in the view
            image.setImageBitmap(bmp);
        };


        // instantiate a image request
        ImageRequest request = new ImageRequest(imageUrl, resp_listener, 500, 500, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565,null);

        // add request to the request queue
        queue.add(request);

        return convertView;
    }
}
