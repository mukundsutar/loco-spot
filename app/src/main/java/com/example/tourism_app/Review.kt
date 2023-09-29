package com.example.tourism_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Review : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        val addrev = findViewById<Button>(R.id.addrev)

        addrev.setOnClickListener()
        {
            val intent = Intent(applicationContext,AddReview::class.java)
            startActivity(intent)
        }

        val showrev = findViewById<Button>(R.id.showrev)
        showrev.setOnClickListener()
        {
            val intent = Intent(applicationContext,ShowReview::class.java)
            startActivity(intent)
        }

        val showvid = findViewById<Button>(R.id.showvid)
        showvid.setOnClickListener()
        {
            val intent = Intent(applicationContext,showvideo::class.java)
            startActivity(intent)
        }
    }
}