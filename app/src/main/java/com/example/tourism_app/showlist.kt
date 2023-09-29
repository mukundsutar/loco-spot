package com.example.tourism_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import java.util.ArrayList

class showlist : AppCompatActivity() {


    private var recyclerView: RecyclerView? = null
    private val db = FirebaseDatabase.getInstance()
    private val root = db.reference.child("Place")

    private var adapter: MyAdapter? = null
    private var list: ArrayList<Items?>? = null
    var ref: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showlist)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.setHasFixedSize(true)
        recyclerView.setLayoutManager(LinearLayoutManager(this))


        list = ArrayList()
        adapter = MyAdapter(this, list)
        recyclerView.setAdapter(adapter)

    }

    override fun onStart() {
        super.onStart()

        root.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children) {
                    val items = dataSnapshot.getValue(
                        Items::class.java
                    )
                    list!!.add(items)
                }
                adapter!!.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }
}