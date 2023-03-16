package com.example.medicube

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userprofile) //binding

        // Get a reference to the "users" node
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase?.getReference("users")


        readUserData()
    }

    private fun readUserData() {

        Config.showDialog(this)

        val currentUser = FirebaseAuth.getInstance().currentUser
        val uid = currentUser?.uid
        val database = FirebaseDatabase.getInstance()
        val userRef = database.getReference("users/$uid")

        userRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val userDetails = dataSnapshot.getValue(UserModel::class.java)

                    Log.w(TAG, "data $userDetails")
                    Config.hideDialog()
                    showData(userDetails)

                } else {
                    Log.w(TAG, "data empty")
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w(TAG, "Failed to read value.")
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