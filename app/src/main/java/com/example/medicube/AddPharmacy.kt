package com.example.medicube

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.medicube.databinding.ActivityAddPharmacyBinding
import com.example.medicube.model.PharmacyData
import com.google.firebase.database.*

class AddPharmacy : AppCompatActivity() {

    private lateinit var binding: ActivityAddPharmacyBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPharmacyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.constraintLayout3.setOnClickListener {
            val name = binding.pname.text.toString().trim()
            val lisence = binding.plisence.text.toString().trim()
            val location = binding.plocation.text.toString().trim()
            val owner = binding.powner.text.toString().trim()

            // Check if any input field is empty
            if (name.isEmpty() || lisence.isEmpty() || location.isEmpty() || owner.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            databaseReference = FirebaseDatabase.getInstance().getReference("Pharmacies")
            val id = databaseReference.push().key // generate a unique id for the medicine
            val pharmacy = PharmacyData(id!!, name, lisence, location, owner) // create a new medicine with the generated id

            // add the new medicine to the database
            databaseReference.child(id).setValue(pharmacy).addOnSuccessListener {
                binding.pname.text?.clear()
                binding.plisence.text?.clear()
                binding.powner.text?.clear()
                binding.plocation.text?.clear()

                Toast.makeText(this, "Data inserted Successfully", Toast.LENGTH_LONG).show()

                // Create an intent to navigate to the target activity
                val intent = Intent(this, PharmacyFeed::class.java)

                // Start the target activity
                startActivity(intent)


            }.addOnFailureListener {
                Toast.makeText(this, "Something went wrong!!", Toast.LENGTH_LONG).show()
            }
        }
    }
}