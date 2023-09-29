package com.example.tourism_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class showfood : AppCompatActivity() {

    var ref: DatabaseReference? = null
    var list: ArrayList<food>? = null
    private var listener: ShowsAdapter.RecyclerViewClickListener? = null

    var recyclerView: RecyclerView? = null

    var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showfood)
        ref = FirebaseDatabase.getInstance().reference.child("Food")
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
                            list!!.add(ds.getValue(food::class.java)!!)
                        }
                        setOnClickListner()
                        val adapter = ShowsAdapter(list, listener)
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

    private fun setOnClickListner() {
        listener = ShowsAdapter.RecyclerViewClickListener { v, position ->
//            val intent = Intent(applicationContext, servicedetails::class.java)
//            intent.putExtra("type", list!![position].category)
//            intent.putExtra("name",list!![position].name)
//            intent.putExtra("des",list!![position].description)
//            intent.putExtra("price",list!![position].price)
//
//            startActivity(intent)
        }
    }

    private fun search(s: String) {

        try{
            val mylist = ArrayList<food?>()
            for (`object` in list!!) {
                if (`object`!!.cityname.toLowerCase().contains(s.toLowerCase())) {
                    mylist.add(`object`)
                }
            }
            val adapter = ShowsAdapter(mylist,listener)
            recyclerView!!.adapter = adapter
        }catch (e:Exception){
            e.printStackTrace()
        }

    }
}