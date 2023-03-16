package com.example.medicube.model

data class UserModel(
    val userid : String? = "",
    val name: String? = "",
    val mobile: String? = "",
    val city: String? = "",
    val country: String? = "",
    val image: String? ="",
    val email: String? ="",

){

    constructor() : this("", "", "", "","","","")


    fun getuuserid(): String? {
        return userid
    }
    fun getuname(): String? {
        return name
    }
    fun getumobile(): String? {
        return mobile
    }
    fun getuemail(): String? {
        return email
    }
    fun getuimage(): String? {
        return image
    }

    fun getucountry(): String? {
        return country
    }
    fun getucity(): String? {
        return city
    }
}
