package com.example.tourism_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText edname,edmono,edemail,edpass;
    Button btn,login;
    FirebaseAuth auth;
    String MY_PREFS_NAME="my_pref_name";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edname=findViewById(R.id.edname);
        edmono=findViewById(R.id.edmono);
        edemail=findViewById(R.id.edemail);
        edpass= findViewById(R.id.edpassword);
        btn=findViewById(R.id.btnregister);
        login=findViewById(R.id.btnlogin);

        auth = FirebaseAuth.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = edname.getText().toString();
                String mono = edmono.getText().toString();
                String email= edemail.getText().toString();
                String pass = edpass.getText().toString();

//                Toast.makeText(MainActivity.this,email,Toast.LENGTH_LONG).show();

                auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(name,mono,email);
                            FirebaseDatabase.getInstance().getReference("user")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(MainActivity.this,"success",Toast.LENGTH_LONG).show();
                                        SharedPreferences preferences = getSharedPreferences("My", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = preferences.edit();
                                        editor.putString("username", name);


                                        editor.apply();
                                        sharedata(email);

                                    }else
                                    {
                                        Toast.makeText(MainActivity.this,"failed",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                    }
                });

            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,login.class);
                startActivity(i);
            }
        });

    }

    private void sharedata(String email) {
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("email", email);

        editor.apply();
        editor.commit();
    }
}