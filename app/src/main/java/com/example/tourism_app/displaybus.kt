package com.example.tourism_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class displaybus : AppCompatActivity() {

    var ref: DatabaseReference? = null
    var list: ArrayList<bus>? = null
    private var listener: ShowpAdapter.RecyclerViewClickListener? = null

    var recyclerView: RecyclerView? = null

    var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_displaybus)

        ref = FirebaseDatabase.getInstance().reference.child("busdetails")
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


                        setOnClickListner()
                        val adapter = ShowpAdapter(list, listener)
                        recyclerView!!.adapter = adapter
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(applicationContext, "error", Toast.LENGTH_SHORT).show()
                }
            })
        }

        if (searchView != null) {
            searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(s: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(s: String): Boolean {
                    search(s)
                    return true
                }
            })
        }

    }

    private fun search(s: String) {
//        Toast.makeText(applicationContext,s.toString(),Toast.LENGTH_LONG).show()
        try{
            val mylist = ArrayList<bus?>()
            for (`object` in list!!) {
                if (`object`!!.destination.toLowerCase().contains(s.toLowerCase())) {
                    mylist.add(`object`)
                }
            }
            val adapter = ShowpAdapter(mylist,listener)
            recyclerView!!.adapter = adapter
        }catch (e:Exception){
            e.printStackTrace()
        }

    }

    private fun setOnClickListner() {

        listener = ShowpAdapter.RecyclerViewClickListener{
            v,position->
            Toast.makeText(applicationContext,"Booked Successfully",Toast.LENGTH_LONG).show()
        }
    }
}
