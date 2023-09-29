package com.example.tourism_app

import android.annotation.SuppressLint
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class budget : AppCompatActivity() {

    var edname: EditText? = null
    var edmenber1:EditText? = null
    var edmono1:EditText? = null
    var edmember2:EditText? = null
    var edmono2:EditText? = null
    var edmember3:EditText? = null
    var edmono3:EditText? = null
    var edamount:EditText?=null






    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_budget)

        edname = findViewById(R.id.edname)
        edmenber1 = findViewById(R.id.edmember1)
        edmono1 = findViewById(R.id.edmobil1)
        edmember2 = findViewById(R.id.edmember2)
        edmono2 = findViewById(R.id.edmobile2)
        edmember3 = findViewById(R.id.edmember3)
        edmono3 = findViewById(R.id.edmobile3)
        edamount = findViewById(R.id.edamount)
        val btn = findViewById<Button>(R.id.btnsubmit)



        btn.setOnClickListener {

            val member1 = edmenber1!!.text.toString()
            val member2 = edmember2!!.text.toString()
            val member3 = edmember3!!.text.toString()
            val mobile1 = edmono1!!.text.toString()
            val mobile2 = edmono2!!.text.toString()
            val mobile3 = edmono3!!.text.toString()

            Toast.makeText(applicationContext,member1,Toast.LENGTH_LONG).show()




            val amount = edamount!!.text.toString()
            val amt = amount.toInt()


            val split = amt / 3

            Toast.makeText(applicationContext,split.toString(), Toast.LENGTH_LONG).show()




            val incomming = "+91$mobile1"
            val sms = SmsManager.getDefault()
            sms.sendTextMessage(incomming, null, "Trip Expense Details $split", null, null)

            val incomming1 = "+91$mobile2"
            val sms1 = SmsManager.getDefault()
            sms1.sendTextMessage(incomming1, null, "Trip Expense Details $split", null, null)

            val incomming2 = "+91$mobile3"
            val sms2 = SmsManager.getDefault()
            sms2.sendTextMessage(incomming2, null, "Trip Expense Details $split", null, null)





        }
    }
}