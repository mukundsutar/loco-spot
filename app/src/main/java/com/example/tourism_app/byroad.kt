package com.example.tourism_app

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class byroad : AppCompatActivity() {

    var edname: EditText? = null

    var edamount: EditText?=null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_byroad)

        edname = findViewById(R.id.edname)
        edamount = findViewById(R.id.edamount)



        val btnnotify = findViewById<Button>(R.id.btnnotify)

        btnnotify.setOnClickListener {
            val i = Intent(applicationContext, popnotify::class.java)
            startActivity(i)
        }

        val btn = findViewById<Button>(R.id.btnsubmit)

        btn.setOnClickListener {

            val source = edname!!.text.toString()
            val des = edamount!!.text.toString()

            try {
                val uri = Uri.parse("https://www.google.co.in/maps/dir/$source/$des")

                val intent = Intent(Intent.ACTION_VIEW,uri)
                intent.setPackage("com.google.android.apps.maps")
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)

            }catch (e: ActivityNotFoundException)
            {
                val uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps")
                val intent = Intent(Intent.ACTION_VIEW,uri)
                intent.setPackage("com.google.android.apps.maps")
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }

    }
}