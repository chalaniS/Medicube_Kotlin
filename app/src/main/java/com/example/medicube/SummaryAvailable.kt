package com.example.medicube

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medicube.model.AvailableData
import com.example.medicube.model.MedicineAdapter
import com.google.firebase.database.*

class SummaryAvailable : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var databaseReference: DatabaseReference
    private lateinit var list: ArrayList<AvailableData>
    private lateinit var adapter: MedicineAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary_available)

        recyclerView = findViewById(R.id.recyclerView)
        databaseReference = FirebaseDatabase.getInstance().getReference("Available Medicines")
        list = ArrayList()
        recyclerView.layoutManager = LinearLayoutManager(this)
//        adapter = MedicineAdapter(this, list, databaseReference)
        adapter = MedicineAdapter(this, list) { medicine ->
            databaseReference.child(medicine.id).removeValue()
        }
        recyclerView.adapter = adapter

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                for (dataSnapshot in snapshot.children) {
                    val medicine = dataSnapshot.getValue(AvailableData::class.java)
                    list.add(medicine!!)
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }
}

//class SummaryAvailable : AppCompatActivity() {
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var databaseReference: DatabaseReference
//    private lateinit var list: ArrayList<AvailableData>
//    private lateinit var adapter: MedicineAdapter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_summary_available)
//
//        recyclerView = findViewById(R.id.recyclerView)
//        databaseReference = FirebaseDatabase.getInstance().getReference("Available Medicines")
//        list = ArrayList()
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        adapter = MedicineAdapter(this, list)
//        recyclerView.adapter = adapter
//
//        databaseReference.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                for (dataSnapshot in snapshot.children) {
//                    val medicine = dataSnapshot.getValue(AvailableData::class.java)
//                    list.add(medicine!!)
//                }
//                adapter.notifyDataSetChanged()
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                // Handle error
//            }
//        })
//    }
//}