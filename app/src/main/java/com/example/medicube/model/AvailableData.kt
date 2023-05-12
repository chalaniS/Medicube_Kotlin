package com.example.medicube.model

class AvailableData(val id: String,
                    val name: String,
                    val weight: String,
                    val qty: String,
                    val desc: String) {
    constructor() : this("", "", "", "", "")
}

//class AvailableData {
//    var mName: String? = null
//    var mWeight: String? = null
//    var mQty: String? = null
//    var mDesc: String? = null
//
//    constructor() {
//        // Required empty public constructor
//    }
//
//    constructor(mName: String?, mWeight: String?, mQty: String?, mDesc: String?) {
//        this.mName = mName
//        this.mWeight = mWeight
//        this.mQty = mQty
//        this.mDesc = mDesc
//    }
//
//    fun getName(): String? {
//        return mName
//    }
//
//    fun getWeight(): String? {
//        return mWeight
//    }
//
//    fun getQty(): String? {
//        return mQty
//    }
//
//    fun getDesc(): String? {
//        return mDesc
//    }
//}

//data class AvailableData(
//    val mName: String? = null,
//    val mWeight: String? = null,
//    val mQty: String? = null,
//    val mDesc: String? = null
//)

//data class AvailableData(
//    var name: String = "",
//    var weight: String = "",
//    var qty: String = "",
//    var desc: String = ""
//)


