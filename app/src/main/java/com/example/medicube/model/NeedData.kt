package com.example.medicube.model


class NeedData(val medicineID: String,
                    val medicineName: String,
                    val medicineWeight: String,
                    val quantity: String,
                    val description: String) {
    constructor() : this("", "", "", "", "")
}
