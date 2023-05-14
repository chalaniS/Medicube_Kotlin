package com.example.medicube.model

class AvailableData(val id: String,
                    val name: String,
                    val weight: String,
                    val qty: String,
                    val desc: String) {
    constructor() : this("", "", "", "", "")
}


