package com.example.lab2;

import android.view.View;

public class GetImageOnClickListener implements View.OnClickListener {

    @Override
    public void onClick(View v){
        //start a new asynctask of type AsyncFlickrJSONData
        new AsyncFlickrJSONData().execute("https://www.flickr.com/services/feeds/photos_public.gne?tags=cats&format=json&nojsoncallback=1");
    }
}
