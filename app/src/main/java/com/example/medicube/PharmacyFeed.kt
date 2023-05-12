package com.example.medicube

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medicube.model.PharmacyData
import com.example.medicube.model.PharmacyAdapter
import com.google.firebase.database.*

class PharmacyFeed : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var databaseReference: DatabaseReference
    private lateinit var list: ArrayList<PharmacyData>
    private lateinit var adapter: PharmacyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pharmacy_feed)

        recyclerView = findViewById(R.id.recyclerView)
        databaseReference = FirebaseDatabase.getInstance().getReference("Pharmacy")
        list = ArrayList()
        recyclerView.layoutManager = LinearLayoutManager(this)
//        adapter = PharmacyAdapter(this, list) { medicine ->
//            onDelete(medicine)
//        }
        recyclerView.adapter = adapter

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                for (dataSnapshot in snapshot.children) {
                    val pharmacy = dataSnapshot.getValue(PharmacyData::class.java)
                    list.add(pharmacy!!)
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })

        val addPharmacyButton = findViewById<ImageView>(R.id.pharmacy_add)
        addPharmacyButton.setOnClickListener {
            val intent = Intent(this, AddPharmacy::class.java)
            startActivity(intent)
        }

    }

//    private fun onDelete(medicine: AvailableData) {
//        val databaseReference = FirebaseDatabase.getInstance().getReference("Available Medicines").child(medicine.id)
//        databaseReference.removeValue()
//    }
}