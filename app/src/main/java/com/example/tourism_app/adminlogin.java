package com.example.tourism_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class adminlogin extends AppCompatActivity {

    EditText email,pass;

Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        btn = findViewById(R.id.login);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = email.getText().toString();
                String password = pass.getText().toString();

                if(mail.equals("admin") && password.equals("123"))

                {

                    Intent i = new Intent(adminlogin.this,Adminhome.class);
                    startActivity(i);
                }
            }
        });
    }
}