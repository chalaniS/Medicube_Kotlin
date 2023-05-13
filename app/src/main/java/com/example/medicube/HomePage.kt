package com.example.medicube

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        // Find the LinearLayout by its ID
        val avl = findViewById<LinearLayout>(R.id.avlbtn)

        // Set a click listener on the LinearLayout
        avl.setOnClickListener {
            // Create an intent to navigate to the target activity
            val intent = Intent(this, SummaryAvailable::class.java)

            // Start the target activity
            startActivity(intent)
        }


        //need medicine navigation
        // Find the LinearLayout by its ID
        val needM = findViewById<LinearLayout>(R.id.needM)

        // Set a click listener on the LinearLayout
        needM.setOnClickListener {
            // Create an intent to navigate to the target activity
            val intent = Intent(this, SummaryNeedMedicine::class.java)

            // Start the target activity
            startActivity(intent)
        }

    }
}