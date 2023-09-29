package com.example.tourism_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class dashboard extends AppCompatActivity {

    Button b1,b2,b3,b4,b5,b6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        b1 = findViewById(R.id.weather);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),weather.class);
                startActivity(i);
            }
        });

        b2 = findViewById(R.id.histo);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),showplace.class);
                startActivity(i);
            }
        });

        b3 = findViewById(R.id.hotel);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),google_map_search.class);
                startActivity(i);
            }
        });

        b4 = findViewById(R.id.chatbot);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),chatbot.class);
                startActivity(i);
            }
        });

        b5 = findViewById(R.id.specialfood);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),showfood.class);
                startActivity(i);
            }
        });

        b6 = findViewById(R.id.travel);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Travel.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.userdash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

//            case R.id.item1: {
//                Intent i = new Intent(getApplicationContext(), weather.class);
//                startActivity(i);
//                return true;
//            }
            case R.id.notify: {
                Intent i1 = new Intent(getApplicationContext(), popnotify.class);
                startActivity(i1);
                return true;
            }
            case R.id.review:{
                Intent i2 = new Intent(getApplicationContext(), Review.class);
                startActivity(i2);
                return true;
            }
            case R.id.logout:{
                Intent i2 = new Intent(getApplicationContext(), login.class);
                startActivity(i2);
                return true;
            }

//            case R.id.item4:{
//                Intent i3 = new Intent(getApplicationContext(), displaybus.class);
//                startActivity(i3);
//                return true;
//            }
//            case R.id.item5:{
//                Intent i3 = new Intent(getApplicationContext(), disflight.class);
//                startActivity(i3);
//                return true;
//            }case R.id.item6:{
//                Intent i3 = new Intent(getApplicationContext(), AddReview.class);
//                startActivity(i3);
//                return true;
//            }case R.id.item7:{
//                Intent i3 = new Intent(getApplicationContext(), ShowReview.class);
//                startActivity(i3);
//                return true;
//            }case R.id.item8:{
//                Intent i3 = new Intent(getApplicationContext(), showvideo.class);
//                startActivity(i3);
//                return true;
//            }
//            case R.id.item9:{
//                Intent i3 = new Intent(getApplicationContext(), chatbot.class);
//                startActivity(i3);
//                return true;
//            }
//            case R.id.item10:{
//                Intent i3 = new Intent(getApplicationContext(), showplace.class);
//                startActivity(i3);
//                return true;
//            }
//


            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
