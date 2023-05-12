package com.example.medicube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.medicube.databinding.ActivityAddAvailableMedicinesBinding
import com.example.medicube.model.AvailableData
import com.google.firebase.database.*

class EditAvailableMedicines : AppCompatActivity() {

//    private lateinit var binding: ActivityAddAvailableMedicinesBinding
//    private lateinit var databaseReference: DatabaseReference
//
//    fun onEdit(savedInstanceState: Bundle?) {
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

}