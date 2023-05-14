package com.example.medicube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Calculation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculation)

        // Get data entry count for Available Medicines
        val databaseRef1 = FirebaseDatabase.getInstance().getReference("Available Medicines")

        databaseRef1.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val avlCount = dataSnapshot.childrenCount
                Log.d("Data Count", "The data entry count for Available Medicines is: $avlCount")

                // Update TextView
                val avlC = findViewById<TextView>(R.id.textView15)
                avlC.text = avlCount.toString()

                // Calculate difference
                val databaseRef2 = FirebaseDatabase.getInstance().getReference("Need Medicines")
                databaseRef2.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val needCount = dataSnapshot.childrenCount
                        Log.d("Data Count", "The data entry count for Need Medicines is: $needCount")

                        // Update TextView
                        val needC = findViewById<TextView>(R.id.teuuxatView15)
                        needC.text = needCount.toString()

                        // Calculate difference and update TextView
                        val differ = needCount - avlCount
                        val cal = findViewById<TextView>(R.id.texstView15)
                        cal.text = differ.toString()
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        Log.e("Error", "Error getting data count: ${databaseError.message}")
                    }
                })
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("Error", "Error getting data count: ${databaseError.message}")
            }
        })
    }
}
