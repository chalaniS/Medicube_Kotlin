package com.example.medicube

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso

class EditUser : AppCompatActivity() {

    private var firebaseDatabase: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editprofile)

        // Get a reference to the Firebase Realtime Database
        firebaseDatabase = FirebaseDatabase.getInstance()

        // Retrieve the user data from the intent
        val name = intent.getStringExtra("name")
        val email = intent.getStringExtra("email")
        val mobile = intent.getStringExtra("mobile")
        val city = intent.getStringExtra("city")
        val country = intent.getStringExtra("country")
        val image = intent.getStringExtra("image")

        // Set the values in the corresponding EditText fields
        findViewById<EditText>(R.id.editTextName).setText(name)
        findViewById<EditText>(R.id.editTextEmail).setText(email)
        findViewById<EditText>(R.id.editTextMobile).setText(mobile)
        findViewById<EditText>(R.id.editTextCity).setText(city)
        findViewById<EditText>(R.id.editTextCountry).setText(country)

        // Load the user's image from the URL and display it in an ImageView using Picasso
        Picasso.get().load(image).into(findViewById<ImageView>(R.id.imageViewUser))

        // Add a click listener to the save button to update the user data in Firebase
        findViewById<Button>(R.id.buttonSave).setOnClickListener {
            saveUserData(name, email, mobile, city, country)
        }



    }

    private fun saveUserData(
        name: String?,
        email: String?,
        mobile: String?,
        city: String?,
        country: String?
    ) {
        // Get the currently logged-in user's ID
        val currentUser = FirebaseAuth.getInstance().currentUser
        val uid = currentUser?.uid

        // Get a reference to the user's data in the database
        val userRef = firebaseDatabase?.getReference("users/$uid")

        // Retrieve the updated values from the UI
        val name = findViewById<EditText>(R.id.editTextName).text.toString()
        val email = findViewById<EditText>(R.id.editTextEmail).text.toString()
        val mobile = findViewById<EditText>(R.id.editTextMobile).text.toString()
        val city = findViewById<EditText>(R.id.editTextCity).text.toString()
        val country = findViewById<EditText>(R.id.editTextCountry).text.toString()

        // Update the user data in the database
        val updatedUserData = HashMap<String, Any>()
        updatedUserData["name"] = name
        updatedUserData["email"] = email
        updatedUserData["mobile"] = mobile
        updatedUserData["city"] = city
        updatedUserData["country"] = country
        userRef?.updateChildren(updatedUserData)?.addOnSuccessListener {
            // User data updated successfully
            Toast.makeText(this, "User data updated successfully", Toast.LENGTH_SHORT).show()
            finish() // Finish the activity and go back to the previous screen
        }?.addOnFailureListener {
            // Error updating user data
            Toast.makeText(this, "Error updating user data: ${it.message}", Toast.LENGTH_SHORT).show()
        }
    }


}