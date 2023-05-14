package com.example.medicube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class EditNeedMedicine : AppCompatActivity() {

    private lateinit var recordId: String
    private lateinit var uname: TextView
    private lateinit var uweight: TextView
    private lateinit var uqty: TextView
    private lateinit var udesc: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_need_medicine)

        // Get the medicine ID from the intent extras
        recordId = intent.getStringExtra("medicine_id") ?: ""

        // Initialize the TextViews
        uname = findViewById(R.id.unsmname)
        uweight = findViewById(R.id.unsmWeight)
        uqty = findViewById(R.id.unsquantity)
        udesc = findViewById(R.id.unsdescription2)

        // Initialize the Buttons
        val submitButton = findViewById<TextView>(R.id.unsbuttons)
        val cancelButton = findViewById<TextView>(R.id.unsbuttonC)

        // Get a reference to your Firebase database
        val database = FirebaseDatabase.getInstance().reference

        // Query the database to retrieve the row of data with the specified ID
        database.child("Need Medicines").orderByKey().equalTo(recordId).get()
            .addOnSuccessListener { dataSnapshot ->
                // Get the first child node
                val lastChild = dataSnapshot.children.lastOrNull()
                if (lastChild == null) {
                    // If the query returns no results, show an error message and finish the activity
                    Toast.makeText(this, "Record not found", Toast.LENGTH_SHORT).show()
                    finish()
                    return@addOnSuccessListener
                }

                // Get the values of the child nodes and convert them to strings
                val name = lastChild.child("medicineName").value?.toString()
                val weight = lastChild.child("medicineWeight").value?.toString()
                val qty = lastChild.child("quantity").value?.toString()
                val desc = lastChild.child("description").value?.toString()

                // Set the values of the TextViews
                uname.text = name
                uweight.text = weight
                uqty.text = qty
                udesc.text = desc

                // Set up the submit button onClick listener
                submitButton.setOnClickListener {
                    // Get the updated input values
                    val name = uname.text.toString()
                    val weight = uweight.text.toString()
                    val qty = uqty.text.toString()
                    val desc = udesc.text.toString()

                    // Update the record with the new values
                    lastChild.ref.updateChildren(
                        mapOf(
                            "medicineName" to name,
                            "medicineWeight" to weight,
                            "quantity" to qty,
                            "description" to desc
                        )
                    )

                    // Show a toast message indicating that the record was updated
                    Toast.makeText(this, "Record updated successfully", Toast.LENGTH_SHORT).show()

                    // Finish the activity and return to the previous screen
                    finish()
                }
            }.addOnFailureListener { exception ->
                // Handle any errors that occur
                Toast.makeText(this, "Error: ${exception.message}", Toast.LENGTH_SHORT).show()
                finish()
            }
    }
}
