package com.example.tourism_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Travel : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_travel)

        val bus = findViewById<Button>(R.id.bus)

        bus.setOnClickListener()
        {
            val intent = Intent(applicationContext,displaybus::class.java)
            startActivity(intent)
        }

        val flight = findViewById<Button>(R.id.flight)

        flight.setOnClickListener()
        {
            val intent = Intent(applicationContext,disflight::class.java)
            startActivity(intent)
        }
    }
}