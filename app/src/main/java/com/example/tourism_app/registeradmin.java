package com.example.tourism_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class registeradmin extends AppCompatActivity  {



    Button btn,login;
    FirebaseAuth auth;
    Spinner spinner;
    EditText edemail,edpass;
    RadioGroup radioGroup;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeradmin);

        edemail=findViewById(R.id.edemail);
        edpass= findViewById(R.id.edpassword);

        login=findViewById(R.id.btnlogin);
        auth = FirebaseAuth.getInstance();

        radioGroup = findViewById(R.id.radiogroup);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name= edemail.getText().toString();
                String pass = edpass.getText().toString();
                if(name.equals("admin") && pass.equals("123"))
                {
                    Intent i = new Intent(registeradmin.this,Adminhome.class);
                    startActivity(i);

                }

            }
        });
    }


    public void checkButton(View view)
    {
        int radioId = radioGroup.getCheckedRadioButtonId();
        if(radioId == R.id.radio_one)
        {

            Toast.makeText(getApplicationContext(),"Admin",Toast.LENGTH_LONG).show();

        }
        if(radioId == R.id.radio_two)
        {
            Toast.makeText(getApplicationContext(),"User",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }

    }



}