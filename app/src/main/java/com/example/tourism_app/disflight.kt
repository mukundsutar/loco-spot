package com.example.tourism_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class disflight : AppCompatActivity() {

    var ref: DatabaseReference? = null
    var list: ArrayList<bus>? = null
    private var listener: DisAdapter.RecyclerViewClickListener? = null

    var recyclerView: RecyclerView? = null

    var searchView: SearchView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disflight)

        ref = FirebaseDatabase.getInstance().reference.child("flightdetails")
        recyclerView = findViewById(R.id.recyclerview)
        searchView = findViewById(R.id.searchview)

    }

    override fun onStart() {
        super.onStart()
        if (ref != null) {
            ref!!.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        list = ArrayList()
                        for (ds in snapshot.children) {
                            list!!.add(ds.getValue(bus::class.java)!!)
                        }
//                        setOnClickListner()
                        val adapter = DisAdapter(list, listener)
                        recyclerView!!.adapter = adapter
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(applicationContext, "error", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}