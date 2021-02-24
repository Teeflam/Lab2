package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity {
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // instantiate a new adapter
        adapter = new MyAdapter();

        // link the list view
        ListView list = findViewById(R.id.list);
        list.setAdapter(adapter);

        // start the async flickr list fetching
        new AsyncFlickrJSONDataForList(adapter).execute("https://www.flickr.com/services/feeds/photos_public.gne?tags=trees&format=json&nojsoncallback=?");
    }
}
