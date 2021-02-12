package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class Flickrapp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flickrapp);

        Button getImage = (Button)findViewById(R.id.getImage);
        getImage.setOnClickListener(new GetImageOnClickListener());
    }
}

