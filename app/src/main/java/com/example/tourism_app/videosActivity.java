package com.example.tourism_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class videosActivity extends AppCompatActivity {

    FloatingActionButton addvideobtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);


        setTitle("User Review");

        addvideobtn = findViewById(R.id.addVideosbtn);

        addvideobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(videosActivity.this,Addvideoactivity.class));


            }
        });
    }
}