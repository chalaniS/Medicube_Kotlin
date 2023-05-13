package com.example.medicube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.firebase.database.FirebaseDatabase

class DeleteUpdate : AppCompatActivity() {

    private lateinit var recordId: String
    private lateinit var uname: TextView
    private lateinit var ulicense : TextView
    private lateinit var ulocation: TextView
    private lateinit var uowner: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_update)

        // Get the medicine ID from the intent extras
        recordId = intent.getStringExtra("pharmacy_id") ?: ""

        // Initialize the TextViews
        uname = findViewById(R.id.uppname)
        ulicense = findViewById(R.id.upplisence)
        ulocation = findViewById(R.id.upplocation)
        uowner = findViewById(R.id.uppowner)

        // Initialize the Buttons
        val submitButton = findViewById<ConstraintLayout>(R.id.upconstraintLayout3)
        val cancelButton = findViewById<ConstraintLayout>(R.id.cancelBtn)

        // Get a reference to your Firebase database
        val database = FirebaseDatabase.getInstance().reference

        // Query the database to retrieve the row of data with the specified ID
        database.child("Pharmacies").orderByKey().equalTo(recordId).get()
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
                val license = lastChild.child("lisence").value?.toString()
                val location = lastChild.child("location").value?.toString()
                val owner = lastChild.child("owner").value?.toString()

                // Set the values of the TextViews
                uname.text = name
                ulicense.text = license
                ulocation.text = location
                uowner.text = owner

                // Set up the submit button onClick listener
                submitButton.setOnClickListener {
                    // Get the updated input values
                    val Name = uname.text.toString()
                    val License = ulicense.text.toString()
                    val Location = ulocation.text.toString()
                    val Owner = uowner.text.toString()

                    // Update the record with the new values
                    lastChild.ref.updateChildren(
                        mapOf(
                            "name" to Name,
                            "weight" to License,
                            "qty" to Location,
                            "desc" to Owner
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