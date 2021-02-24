package com.example.lab2;

import android.view.View;
import android.widget.ImageView;

public class GetImageOnClickListener implements View.OnClickListener {
    private ImageView image;

    public  GetImageOnClickListener(ImageView image){
        // assign the image view
        this.image= image;
    }

    @Override
    public void onClick(View v){
        //start a new asynctask of type AsyncFlickrJSONData
        new AsyncFlickrJSONData(new AsyncBitmapDownloader(image)).execute("https://www.flickr.com/services/feeds/photos_public.gne?tags=trees&format=json&nojsoncallback=?");
    }
}
