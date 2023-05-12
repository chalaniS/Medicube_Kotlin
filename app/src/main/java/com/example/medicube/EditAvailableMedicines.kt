package com.example.medicube

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.medicube.databinding.ActivityAddAvailableMedicinesBinding
import com.example.medicube.model.AvailableData
import com.google.firebase.database.*

class EditAvailableMedicines : AppCompatActivity() {

    private lateinit var recordId: String
    private lateinit var uname: TextView
    private lateinit var uweight: TextView
    private lateinit var uqty: TextView
    private lateinit var udesc: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_available_medicines)

        // Get the medicine ID from the intent extras
        recordId = intent.getStringExtra("medicine_id") ?: ""

        // Initialize the TextViews
        uname = findViewById(R.id.umName)
        uweight = findViewById(R.id.umWeight)
        uqty = findViewById(R.id.umQty)
        udesc = findViewById(R.id.umDesc)

        // Initialize the Buttons
        val submitButton = findViewById<Button>(R.id.usaveBtn)
        val cancelButton = findViewById<Button>(R.id.ucancelBtn)

        // Get a reference to your Firebase database
        val database = FirebaseDatabase.getInstance().reference

        // Query the database to retrieve the row of data with the specified ID
        database.child("Available Medicines").orderByKey().equalTo(recordId).get()
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
                val name = lastChild.child("name").value?.toString()
                val weight = lastChild.child("weight").value?.toString()
                val qty = lastChild.child("qty").value?.toString()
                val decs = lastChild.child("desc").value?.toString()

                // Set the values of the TextViews
                uname.text = name
                uweight.text = weight
                uqty.text = qty
                udesc.text = decs

                // Set up the submit button onClick listener
                submitButton.setOnClickListener {
                    // Get the updated input values
                    val Name = uname.text.toString()
                    val Weight = uweight.text.toString()
                    val Qty = uqty.text.toString()
                    val Desc = udesc.text.toString()

                    // Update the record with the new values
                    lastChild.ref.updateChildren(
                        mapOf(
                            "name" to Name,
                            "weight" to Weight,
                            "qty" to Qty,
                            "desc" to Desc
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
