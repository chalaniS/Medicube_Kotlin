package com.example.medicube

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout

class Needmedicine : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_needmedicine)

        // Find the LinearLayout by its ID
        val addnm = findViewById<FrameLayout>(R.id.addnMnav)

        // Set a click listener on the LinearLayout
        addnm.setOnClickListener {
            // Create an intent to navigate to the target activity
            val intent = Intent(this, AddNeedMedicine::class.java)

            // Start the target activity
            startActivity(intent)
        }


    }
}