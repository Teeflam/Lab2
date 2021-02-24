package com.example.lab2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Vector;

public class MyAdapter extends BaseAdapter {
    private Vector<String> imageUrls;

    public MyAdapter() {
        // initialize the vector
        imageUrls = new Vector<>();
    }

    // add url in the vector
    public void add(String url) {
        imageUrls.add(url);
        // show the url which is added
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
        // inflate the layout for each url
        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.textviewlayout, parent, false);
        }
        // retrieve the image url
        String imageUrl = (String) getItem(position);

        // retrieve the text view
        TextView url = convertView.findViewById(R.id.url_text);

        // set the url
        url.setText(imageUrl);

        return convertView;
    }
}
