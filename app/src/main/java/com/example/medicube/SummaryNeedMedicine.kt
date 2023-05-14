package com.example.medicube

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medicube.model.AvailableData
import com.example.medicube.model.MedicineAdapter
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
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = NeedAdapter(this, list) { medicine ->
            onDelete(medicine)
        }
        recyclerView.adapter = adapter

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                for (dataSnapshot in snapshot.children) {
                    val medicine = dataSnapshot.getValue(NeedData::class.java)
                    list.add(medicine!!)
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })

        val addMedicineButton = findViewById<Button>(R.id.addmnnav)
        addMedicineButton.setOnClickListener {
            val intent = Intent(this, AddNeedMedicine::class.java)
            startActivity(intent)
        }

    }

    private fun onDelete(medicine: NeedData) {
        val databaseReference = FirebaseDatabase.getInstance().getReference("Need Medicines").child(medicine.medicineID)
        databaseReference.removeValue()
    }
}