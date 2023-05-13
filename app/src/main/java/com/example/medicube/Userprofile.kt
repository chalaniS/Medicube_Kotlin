package com.example.medicube

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.medicube.model.UserModel
import com.example.medicube.utils.Config
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso

class Userprofile : AppCompatActivity() {

    private var firebaseDatabase: FirebaseDatabase? = null
    private var databaseReference: DatabaseReference? = null
    private var userDetails: UserModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userprofile)

        // Get a reference to the "users" node
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase?.getReference("users")

        readUserData()

        val editButton = findViewById<Button>(R.id.usereditbtn)
        editButton.setOnClickListener {
            val intent = Intent(this, Editprofile::class.java)
            // Pass the user details to the EditprofileActivity
            intent.putExtra("name", userDetails?.name)
            intent.putExtra("email", userDetails?.email)
            intent.putExtra("mobile", userDetails?.mobile)
            intent.putExtra("city", userDetails?.city)
            intent.putExtra("country", userDetails?.country)
            intent.putExtra("image", userDetails?.image)
            startActivity(intent)

        }

        // Add a click listener to the delete button to delete the user's data from Firebase
        findViewById<Button>(R.id.userdeletebtn).setOnClickListener {
            // Get the currently logged-in user's ID
            val currentUser = FirebaseAuth.getInstance().currentUser
            val uid = currentUser?.uid

            // Get a reference to the user's data in the database
            val userRef = firebaseDatabase?.getReference("users/$uid")

            // Delete the user's data from the database
            userRef?.removeValue()?.addOnSuccessListener {
                // User data deleted successfully
                Toast.makeText(this, "User data deleted successfully", Toast.LENGTH_SHORT).show()
//                finish() // Finish the activity and go back to the previous screen
                // Navigate to the login page
                val intent = Intent(this, SignInActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)

            }?.addOnFailureListener {
                // Error deleting user data
                Toast.makeText(this, "Error deleting user data: ${it.message}", Toast.LENGTH_SHORT).show()


            }
        }
    }

    private fun readUserData() {
        // Show a progress dialog while loading the user data
        Config.showDialog(this)

        // Get the currently logged-in user's ID
        val currentUser = FirebaseAuth.getInstance().currentUser
        val uid = currentUser?.uid

        // Get a reference to the user's data in the database
        val userRef = firebaseDatabase?.getReference("users/$uid")

        // Read the user's data from the database
        userRef?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Convert the data snapshot to a UserModel object
                    userDetails = dataSnapshot.getValue(UserModel::class.java)
                    Log.d(TAG, "User data: $userDetails")
                    // Hide the progress dialog
                    Config.hideDialog()
                    // Display the user data on the screen
                    showData(userDetails)
                } else {
                    Log.d(TAG, "User data not found")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e(TAG, "Error reading user data", databaseError.toException())
            }
        })
    }


    private fun showData(userDetails: UserModel?) {

        var name = findViewById<TextView>(R.id.fullname)
        var email = findViewById<TextView>(R.id.email)
        var mobileNumber = findViewById<TextView>(R.id.mobileNumber)
        var city = findViewById<TextView>(R.id.city)
        val userImageView = findViewById<ImageView>(R.id.userprofile)
        Picasso.get().load(userDetails?.image).into(userImageView)

        var country = findViewById<TextView>(R.id.ukcountry)

        name.text = userDetails?.name
        email.text = userDetails?.email
        mobileNumber.text = userDetails?.mobile
        city.text = userDetails?.city
        country.text = userDetails?.country
        name.text = userDetails?.name

    }
}