package com.example.medicube

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medicube.model.NeedAdapter
import com.example.medicube.model.NeedData
import com.google.firebase.database.*

class SummaryNeedMedicine : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var databaseReference: DatabaseReference
    private lateinit var list: ArrayList<NeedData>
    private lateinit var adapter: NeedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary_need_medicine)

        recyclerView = findViewById(R.id.recycle)
        databaseReference = FirebaseDatabase.getInstance().getReference("Need Medicines")
        list = ArrayList()
        adapter = NeedAdapter(this, list, ::onDelete) { medicine ->
            //handle edit button click
            val intent = Intent(this, EditNeedMedicine::class.java)
            intent.putExtra("medicine_id", medicine.medicineID)
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Find the Button by its ID
        val addnm = findViewById<Button>(R.id.addmnnav)

        // Set a click listener on the Button
        addnm.setOnClickListener {
            // Create an intent to navigate to the target activity
            val intent = Intent(this, AddNeedMedicine::class.java)

            // Start the target activity
            startActivity(intent)
        }

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                for (dataSnapshot in snapshot.children) {
                    val medicine = dataSnapshot.getValue(NeedData::class.java)
                    medicine?.let { list.add(it) }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    private fun onDelete(medicine: NeedData) {
        val databaseReference = FirebaseDatabase.getInstance().getReference("Need Medicines").child(medicine.medicineID)
        databaseReference.removeValue()
    }
}
