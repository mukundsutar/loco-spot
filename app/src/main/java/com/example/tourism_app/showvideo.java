package com.example.tourism_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class showvideo extends AppCompatActivity {

    private ArrayList<member> videoArrayList;
    private AdapterVideo adapterVideo;
    private RecyclerView recyclerView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showvideo);


        recyclerView = findViewById(R.id.recyclerview);
        
        loadvideofromfirebase();


    }

    private void loadvideofromfirebase() {

        videoArrayList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Videos");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    member m = ds.getValue(member.class);

                    videoArrayList.add(m);
                }

                adapterVideo = new AdapterVideo(showvideo.this,videoArrayList);
                recyclerView.setAdapter(adapterVideo);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}