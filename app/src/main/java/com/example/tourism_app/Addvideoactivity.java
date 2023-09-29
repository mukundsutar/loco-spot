package com.example.tourism_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class Addvideoactivity extends AppCompatActivity {

    private ActionBar actionBar;

    private EditText title;
    private VideoView videoView;
    private Button btnupload;

    private FloatingActionButton floatingActionButton;
    private static final int GALLERY_CODE =100;
    private static final int CAMERA_CODE = 101;
    private static final int CAMERA_REQUEST_CODE =102;
    private String[] cameraPermissions;
    private Uri videoUri = null;
    private ProgressDialog progressDialog;
    String name;





    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addvideoactivity);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Add New Review");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        title = findViewById(R.id.edname);
        videoView = findViewById(R.id.video);
        btnupload = findViewById(R.id.upload);
        floatingActionButton = findViewById(R.id.pickvideofab);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Uploading...");
        progressDialog.setCanceledOnTouchOutside(false);


        cameraPermissions = new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};


        btnupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                 name = title.getText().toString().trim();

                if(TextUtils.isEmpty(name))
                {
                    Toast.makeText(getApplicationContext(),"name id required",Toast.LENGTH_LONG).show();
                }

                else if(videoUri == null)
                {
                    Toast.makeText(getApplicationContext(),"Video id required",Toast.LENGTH_LONG).show();
                }else {
                    uploadVideoFirebase();
                }

            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoPickupDialog();
            }
        });







    }

    private void uploadVideoFirebase() {

        progressDialog.show();

        String timestamp = ""+System.currentTimeMillis();
        String filepath = "Videos/" + "video_" +timestamp;
        StorageReference storageReference = FirebaseStorage.getInstance().getReference(filepath);
        storageReference.putFile(videoUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while(!uriTask.isSuccessful());
                        Uri downloadUri = uriTask.getResult();
                        if(uriTask.isSuccessful())
                        {
                                HashMap<String,Object> hashMap = new HashMap<>();
                                hashMap.put("title", ""+name);
                                hashMap.put("id",""+timestamp);
                                hashMap.put("timestamp",""+timestamp);
                                hashMap.put("videoUrl",""+downloadUri);

                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Videos");
                            databaseReference.child(timestamp)
                                    .setValue(hashMap)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {

                                            progressDialog.dismiss();
                                            Toast.makeText(getApplicationContext(),"Video Uploaded",Toast.LENGTH_LONG).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

                                            progressDialog.dismiss();
                                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                                        }
                                    });
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void videoPickupDialog() {
        String [] options ={"Camera" , "Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick Video From")
                .setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which== 0){

                            if(!chechCameraPermission())
                            {
                                requestCameraPermission();
                            }else {
                                videoPickCamera();
                            }


                        }else if(which == 1)
                        {
                            videoPickGallery();

                        }
                    }
                }).show();
    }

    private void requestCameraPermission()
    {
        ActivityCompat.requestPermissions(this,cameraPermissions,CAMERA_REQUEST_CODE);

    }

    private Boolean chechCameraPermission()
    {
        boolean result1 = ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
        boolean result2 = ContextCompat.checkSelfPermission(this,Manifest.permission.WAKE_LOCK) == PackageManager.PERMISSION_GRANTED;

      return   result1 && result2;

    }

    private void videoPickGallery()
    {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Videos"),GALLERY_CODE);



    }

    private void videoPickCamera()
    {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(intent,CAMERA_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch(requestCode)
        {
            case CAMERA_REQUEST_CODE:
                if(grantResults.length>0)
                {
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if(cameraAccepted && storageAccepted)
                    {
                        videoPickCamera();
                    }else {
                        Toast.makeText(getApplicationContext(),"Permissions Required",Toast.LENGTH_LONG).show();
                    }

                }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK)
        {
            if(requestCode == GALLERY_CODE)
            {
                videoUri = data.getData();
                setVideoToVideoView();
            }
        }else if(requestCode == CAMERA_CODE)
        {
            videoUri = data.getData();
            setVideoToVideoView();
        }
    }

    private void setVideoToVideoView() {

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(videoUri);
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

                videoView.pause();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();

    }
}