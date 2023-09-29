package com.example.tourism_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class userdashboard : AppCompatActivity() {


    var name:String?=null
    var address:String?=null
    var mobileno:String?=null
    var item1:String?=null
    var price1:String?=null
    var item2:String?=null
    var price2:String?=null
    var item3:String?=null
    var price3:String?=null
    var url:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userdashboard)

        val txtname = findViewById<TextView>(R.id.txtname)
        val txtaddress = findViewById<TextView>(R.id.txtaddress)
        val txtmobile = findViewById<TextView>(R.id.txtmobile)
        val txtitem1 = findViewById<TextView>(R.id.txtitem1)
        val txtprice1 = findViewById<TextView>(R.id.txtprice1)
        val txtitem2 = findViewById<TextView>(R.id.txtitem2)
        val txtprice2 = findViewById<TextView>(R.id.txtprice2)
        val txtitem3 = findViewById<TextView>(R.id.txtitem3)
        val txtprice3= findViewById<TextView>(R.id.txtprice3)
        val image = findViewById<ImageView>(R.id.image1)
        val bundle = intent.extras



        name = bundle!!.getString("cityname")
        address = bundle.getString("place1")
        mobileno = bundle.getString("des1")
        item1=bundle.getString("place2")
        price1 = bundle.getString("des2")
        item2=bundle.getString("place3")
        price2 = bundle.getString("des3")
        item3 = bundle.getString("place4")
        price3 = bundle.getString("des4")
        url = bundle.getString("url")


        val btn = findViewById<Button>(R.id.btnsend)

        btn.setOnClickListener {
            val intent = Intent(applicationContext,popnotify::class.java)
            startActivity(intent)
        }




        Glide.with(this@userdashboard).load(url).into(image)

        txtname.setText("City Name Name: "+name)
        txtaddress.setText("Place1: " +address)
        txtmobile.setText("Description: "+mobileno)
        txtitem1.setText("Place2: " + item1)
        txtprice1.setText("Description: "+price1)
        txtitem2.setText("Place3:  "+item2)
        txtprice2.setText("Description: "+price2)
        txtitem3.setText("Place4: "+item3)
        txtprice3.setText("Description: "+price3)


    }
}