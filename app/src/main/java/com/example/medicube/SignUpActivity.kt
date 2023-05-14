package com.example.medicube

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.medicube.databinding.ActivitySignUpBinding
import com.example.medicube.model.UserModel
import com.example.medicube.utils.Config
import com.example.medicube.utils.Config.hideDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage


class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth


    //image upload
    private var imageUri : Uri? = null

    private val selectImage = registerForActivityResult(ActivityResultContracts.GetContent()){
        imageUri = it
        binding.userImage.setImageURI(imageUri)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        //image upload
        binding.userImage.setOnClickListener{
            selectImage.launch("image/*")
        }

        //last textview navigation
        binding.login.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        binding.saveData.setOnClickListener {
            print("Im in onclicklister")
            validateData()
        }
    }



    private fun validateData() {

        print("Im in validateData")
        val fname = binding.fullname.text.toString()
        val mobile = binding.mobile.text.toString()
        val city = binding.ucity.text.toString()
        val country = binding.country.text.toString()
        val email = binding.emailEt.text.toString()
        val pass = binding.passET.text.toString()
        val confirmPass = binding.confirmPassEt.text.toString()

        print("Data from user$fname$mobile$email$pass$confirmPass")


        if (fname.isNotEmpty() && mobile.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty() && city.isNotEmpty() && country.isNotEmpty() && confirmPass.isNotEmpty() && imageUri != null) {

                if (pass == confirmPass) {

                print("passwords matched")

                firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {

                    if (it.isSuccessful) {
                        Toast.makeText(this, "Successfully Sign In" , Toast.LENGTH_SHORT).show()
                        insertimage()

                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                    }
                }
            } else {
                Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

        }
    }


    private fun insertimage() {

        Config.showDialog(this)

        val userId =  FirebaseAuth.getInstance().currentUser?.uid
        val imageId = "img" + FirebaseAuth.getInstance().currentUser?.uid
        val storageRef = Firebase.storage.reference.child("images").child(userId!!).child(imageId)
        val uploadTask = storageRef.putFile(imageUri!!)

        uploadTask
            .addOnSuccessListener {
                storageRef.downloadUrl.addOnSuccessListener {
                    Toast.makeText(this, "Image uploaded", Toast.LENGTH_SHORT).show()
                    storageData(it)

                } .addOnFailureListener{
                    hideDialog()
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener{
                hideDialog()
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
    }

    private fun storageData(imageUrl: Uri?) {
        val data = UserModel(
            userid = firebaseAuth.currentUser!!.uid,
            name = binding.fullname.text.toString(),
            mobile = binding.mobile.text.toString (),
            email = binding.emailEt.text.toString(),
            country = binding.country.text.toString(),
            city = binding.ucity.text.toString(),
            image = imageUrl.toString(),
        )

        print("Im in storageData()")

        val uId = firebaseAuth.currentUser!!.uid
        val database: DatabaseReference = FirebaseDatabase.getInstance().reference

        val userData = mapOf(
            "userId" to data.getuuserid(),
            "name" to data.getuname(),
            "email" to data.getuemail(),
            "mobile" to data.getumobile(),
            "image" to data.getuimage(),
            "city" to data.getucity(),
            "country" to data.getucountry()
        )

        val uploadData =  database.child("users").child(uId).setValue(userData)

        uploadData.addOnCompleteListener{

                hideDialog()

                if(it.isSuccessful){

                    print("Im in image uploaded")

                    //after path should be added
                    startActivity(Intent(this, SignInActivity::class.java))
                    finish()
                    Toast.makeText(this, "User Sign In Successfull", Toast.LENGTH_SHORT).show()

                } else{
                    Toast.makeText(this, it.exception!!.message, Toast.LENGTH_SHORT).show()

                }
            }
    }


}