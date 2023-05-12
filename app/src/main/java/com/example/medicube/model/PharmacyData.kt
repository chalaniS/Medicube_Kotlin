package com.example.medicube.model

class PharmacyData(val id: String,
                    val name: String,
                    val lisence: String,
                    val location: String,
                    val owner: String) {
    constructor() : this("", "", "", "", "")
}