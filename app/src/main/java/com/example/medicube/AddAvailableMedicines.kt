package com.example.medicube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.medicube.databinding.ActivityAddAvailableMedicinesBinding
import com.example.medicube.model.AvailableData
import com.google.firebase.database.*

class AddAvailableMedicines : AppCompatActivity() {

    private lateinit var binding: ActivityAddAvailableMedicinesBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddAvailableMedicinesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitBtn.setOnClickListener {
            val name = binding.mName.text.toString().trim()
            val weight = binding.mWeight.text.toString().trim()
            val qty = binding.mQty.text.toString().trim()
            val desc = binding.mDesc.text.toString().trim()

            // Check if any input field is empty
            if (name.isEmpty() || weight.isEmpty() || qty.isEmpty() || desc.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            databaseReference = FirebaseDatabase.getInstance().getReference("Available Medicines")
            val id = databaseReference.push().key // generate a unique id for the medicine
            val medicine = AvailableData(id!!, name, weight, qty, desc) // create a new medicine with the generated id

            // add the new medicine to the database
            databaseReference.child(id).setValue(medicine).addOnSuccessListener {
                binding.mName.text?.clear()
                binding.mWeight.text?.clear()
                binding.mQty.text?.clear()
                binding.mDesc.text?.clear()

                Toast.makeText(this, "Data inserted Successfully", Toast.LENGTH_LONG).show()

            }.addOnFailureListener {
                Toast.makeText(this, "Something went wrong!!", Toast.LENGTH_LONG).show()
            }
        }
    }
}


//class AddAvailableMedicines : AppCompatActivity() {
//
//    private lateinit var binding: ActivityAddAvailableMedicinesBinding
//    private lateinit var databaseReference: DatabaseReference
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityAddAvailableMedicinesBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        binding.submitBtn.setOnClickListener {
//            val name = binding.mName.text.toString()
//            val weight = binding.mWeight.text.toString()
//            val qty = binding.mQty.text.toString()
//            val desc = binding.mDesc.text.toString()
//
//            databaseReference = FirebaseDatabase.getInstance().getReference("Available Medicines")
//            val id = databaseReference.push().key // generate a unique id for the medicine
//            val medicine = AvailableData(id!!, name, weight, qty, desc) // create a new medicine with the generated id
//
//            // add the new medicine to the database
//            databaseReference.child(id).setValue(medicine).addOnSuccessListener {
//                binding.mName.text?.clear()
//                binding.mWeight.text?.clear()
//                binding.mQty.text?.clear()
//                binding.mDesc.text?.clear()
//
//                Toast.makeText(this, "Data inserted Successfully", Toast.LENGTH_LONG).show()
//
//            }.addOnFailureListener {
//                Toast.makeText(this, "Something went wrong!!", Toast.LENGTH_LONG).show()
//            }
//        }
//    }
//}

//class AddAvailableMedicines : AppCompatActivity() {
//
//    private lateinit var binding: ActivityAddAvailableMedicinesBinding
//    private lateinit var databaseReference: DatabaseReference
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityAddAvailableMedicinesBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        binding.submitBtn.setOnClickListener {
//            val name = binding.mName.text.toString()
//            val weight = binding.mWeight.text.toString()
//            val qty = binding.mQty.text.toString()
//            val desc = binding.mDesc.text.toString()
//
//            databaseReference = FirebaseDatabase.getInstance().getReference("Available Medicines")
//            val medicines = AvailableData(name, weight, qty, desc)
//            databaseReference.child(name).setValue(medicines).addOnSuccessListener {
//                binding.mName.text?.clear()
//                binding.mWeight.text?.clear()
//                binding.mQty.text?.clear()
//                binding.mDesc.text?.clear()
//
//                Toast.makeText(this, "Data inserted Successfully", Toast.LENGTH_LONG).show()
//
//            }.addOnFailureListener {
//                Toast.makeText(this, "Something went wrong!!", Toast.LENGTH_LONG).show()
//            }
//        }
//    }
//
//}