package com.example.medicube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.medicube.databinding.ActivityAddNeedMedicineBinding
import com.example.medicube.model.NeedData
import com.google.firebase.database.*
import android.content.Intent

class AddNeedMedicine : AppCompatActivity() {

    private lateinit var binding: ActivityAddNeedMedicineBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNeedMedicineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttons.setOnClickListener {
            val medicinename = binding.mname.text.toString().trim()
            val weight = binding.mWeight.text.toString().trim()
            val qty = binding.quantity.text.toString().trim()
            val desc = binding.description.text.toString().trim()

            // Check if any input field is empty
            if (medicinename.isEmpty() || weight.isEmpty() || qty.isEmpty() || desc.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            databaseReference = FirebaseDatabase.getInstance().getReference("Need Medicines")
            val id = databaseReference.push().key // generate a unique id for the medicine
            val medicine = NeedData(id!!, medicinename, weight, qty, desc) // create a new medicine with the generated id

            // add the new medicine to the database
            if (id != null) {
                databaseReference.child(id).setValue(medicine).addOnSuccessListener {
                    binding.mname.text?.clear()
                    binding.mWeight.text?.clear()
                    binding.quantity.text?.clear()
                    binding.description.text?.clear()

                    Toast.makeText(this, "Data inserted Successfully", Toast.LENGTH_LONG).show()

                    // Navigate to SummaryNeedMedicine page
                    val intent = Intent(this, SummaryNeedMedicine::class.java)
                    startActivity(intent)

                    finish()


                }.addOnFailureListener {
                    Toast.makeText(this, "Something went wrong!!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun NeedData(id: String, name: String, qty: String, desc: String): Any? {
        TODO("Not yet implemented")
    }
}

