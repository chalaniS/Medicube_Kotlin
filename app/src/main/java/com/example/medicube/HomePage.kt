package com.example.medicube

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.google.firebase.auth.FirebaseAuth

class HomePage : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        firebaseAuth = FirebaseAuth.getInstance() // Initialize firebaseAuth

        // Find the LinearLayout by its ID
        val avl = findViewById<LinearLayout>(R.id.phabtn)

        // Set a click listener on the LinearLayout
        avl.setOnClickListener {
            // Create an intent to navigate to the target activity
            val intent = Intent(this, PharmacyFeed::class.java)
            intent.putExtra("userId", firebaseAuth.currentUser?.uid)

            // Start the target activity
            startActivity(intent)
        }
    }
}
