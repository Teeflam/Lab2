package com.example.lab2;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

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
    }

    @Override
    public int getCount() {
        return imageUrls.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("JFL", "TODO");

        return null;
    }
}
