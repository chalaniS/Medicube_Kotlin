package com.example.medicube.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medicube.R
import com.google.firebase.database.*

class MedicineAdapter(
    private val context: Context,
    private val list: ArrayList<AvailableData>,
    private val onDelete: (AvailableData) -> Unit
) : RecyclerView.Adapter<MedicineAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mName: TextView = itemView.findViewById(R.id.txt_mName)
        var mWeight: TextView = itemView.findViewById(R.id.txt_mWeight)
        var mQty: TextView = itemView.findViewById(R.id.txt_mQty)
        var mDesc: TextView = itemView.findViewById(R.id.txt_mDesc)
        var mDeleteButton: Button = itemView.findViewById(R.id.deleteBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.summary_item, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val medicine = list[position]
        holder.mName.text = medicine.name
        holder.mWeight.text = medicine.weight
        holder.mQty.text = medicine.qty.toString()
        holder.mDesc.text = medicine.desc

        holder.mDeleteButton.setOnClickListener {
            // Remove the item from the list
            list.removeAt(position)

            // Call the onDelete function to remove the item from the database
            onDelete(medicine)

            // Notify the adapter that the item has been removed
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

//class MedicineAdapter(
//    private val context: Context,
//    private val list: ArrayList<AvailableData>,
//    private val databaseReference: DatabaseReference
//) : RecyclerView.Adapter<MedicineAdapter.MyViewHolder>() {
//
//    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var mName: TextView = itemView.findViewById(R.id.txt_mName)
//        var mWeight: TextView = itemView.findViewById(R.id.txt_mWeight)
//        var mQty: TextView = itemView.findViewById(R.id.txt_mQty)
//        var mDesc: TextView = itemView.findViewById(R.id.txt_mDesc)
//        var mDeleteButton: Button = itemView.findViewById(R.id.deleteBtn)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val v = LayoutInflater.from(context).inflate(R.layout.summary_item, parent, false)
//        return MyViewHolder(v)
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val medicine = list[position]
//        holder.mName.text = medicine.name
//        holder.mWeight.text = medicine.weight
//        holder.mQty.text = medicine.qty
//        holder.mDesc.text = medicine.desc
//
//        holder.mDeleteButton.setOnClickListener {
//            // Remove the item from the list
//            list.removeAt(position)
//
//            // Update the database
//            databaseReference.child(medicine.id).removeValue()
//
//            // Notify the adapter that the item has been removed
//            notifyDataSetChanged()
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return list.size
//    }
//}

//class MedicineAdapter(private val context: Context, private val list: ArrayList<AvailableData>) :
//    RecyclerView.Adapter<MedicineAdapter.MyViewHolder>() {
//
//    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var mName: TextView = itemView.findViewById(R.id.txt_mName)
//        var mWeight: TextView = itemView.findViewById(R.id.txt_mWeight)
//        var mQty: TextView = itemView.findViewById(R.id.txt_mQty)
//        var mDesc: TextView = itemView.findViewById(R.id.txt_mDesc)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val v = LayoutInflater.from(context).inflate(R.layout.summary_item, parent, false)
//        return MyViewHolder(v)
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val medicine = list[position]
//        holder.mName.text = medicine.name
//        holder.mWeight.text = medicine.weight
//        holder.mQty.text = medicine.qty.toString()
//        holder.mDesc.text = medicine.desc
//    }
//
//    override fun getItemCount(): Int {
//        return list.size
//    }
//}

//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.medicube.R
//
//class MedicineAdapter(private val context: Context, private val list: ArrayList<AvailableData>) :
//    RecyclerView.Adapter<MedicineAdapter.MyViewHolder>() {
//
//    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var mName: TextView = itemView.findViewById(R.id.txt_mName)
//        var mWeight: TextView = itemView.findViewById(R.id.txt_mWeight)
//        var mQty: TextView = itemView.findViewById(R.id.txt_mQty)
//        var mDesc: TextView = itemView.findViewById(R.id.txt_mDesc)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val v = LayoutInflater.from(context).inflate(R.layout.summary_item, parent, false)
//        return MyViewHolder(v)
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val medicine = list[position]
//        holder.mName.text = medicine.getName()
//        holder.mWeight.text = medicine.getWeight()
//        holder.mQty.text = medicine.getQty()
//        holder.mDesc.text = medicine.getDesc()
//    }
//
//    override fun getItemCount(): Int {
//        return list.size
//    }
//}