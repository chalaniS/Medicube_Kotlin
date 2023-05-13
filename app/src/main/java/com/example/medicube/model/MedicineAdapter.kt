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
import android.content.Intent
import com.example.medicube.EditAvailableMedicines


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
        var mDeleteButton: Button = itemView.findViewById(R.id.ndeleteBtn)
        var mUpdateButton: Button = itemView.findViewById(R.id.mdatebtn)

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
            onDelete(medicine)
        }

        holder.mUpdateButton.setOnClickListener {
            val intent = Intent(context, EditAvailableMedicines::class.java)
            intent.putExtra("medicine_id", medicine.id)
            context.startActivity(intent)
        }

    }


    override fun getItemCount(): Int {
        return list.size
    }
}
