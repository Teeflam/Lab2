package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;

public class Flickrapp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flickrapp);

        //get the image container
        ImageView image = findViewById(R.id.image);

        Button getImage = (Button) findViewById(R.id.getImage);
        getImage.setOnClickListener(new GetImageOnClickListener(image));
    }

    // handle list activity
    public void onClickList(View v) {
        // start new activity
        Intent list = new Intent(getApplicationContext(), ListActivity.class);
        startActivity(list);
    }
}


