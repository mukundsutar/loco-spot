package com.example.tourism_app

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class addbus : AppCompatActivity() {
    var edname: EditText? = null
    var edmenber1: EditText? = null
    var edmono1: EditText? = null
    var edmember2: EditText? = null
    var edmono2: EditText? = null
    var edmember3: EditText? = null
    var edmono3: EditText? = null
    var edamount: EditText?=null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addbus)

        edname = findViewById(R.id.edname)
//        edmenber1 = findViewById(R.id.edmember1)
        edmono1 = findViewById(R.id.edmobil1)
        edmember2 = findViewById(R.id.edmember2)
//        edmono2 = findViewById(R.id.edmobile2)
//        edmember3 = findViewById(R.id.edmember3)
//        edmono3 = findViewById(R.id.edmobile3)
        edamount = findViewById(R.id.edamount)

        val btn = findViewById<Button>(R.id.btnsubmit)

        btn.setOnClickListener {
            val name = edname!!.text.toString()
//            val member1 = edmenber1!!.text.toString()
            val member2 = edmember2!!.text.toString()

            val mobile1 = edmono1!!.text.toString()
            val amount = edamount!!.text.toString()


            val data = FirebaseDatabase.getInstance().reference.child("busdetails")
            val service = bus(name,amount,mobile1,member2)
            data.push().setValue(service)

            Toast.makeText(applicationContext,"Uploaded", Toast.LENGTH_LONG).show()
        }



    }
    }
