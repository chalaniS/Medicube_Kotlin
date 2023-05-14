package com.example.medicube.model

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medicube.EditAvailableMedicines
import com.example.medicube.EditNeedMedicine
import com.example.medicube.R

class NeedAdapter(
    private val context: Context,
    private val list: ArrayList<NeedData>,
    private val onDelete: (NeedData) -> Unit
) : RecyclerView.Adapter<NeedAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nName: TextView = itemView.findViewById(R.id.mediname)
        var nWeight: TextView = itemView.findViewById(R.id.weight)
        var nQty: TextView = itemView.findViewById(R.id.qty)
        var nDesc: TextView = itemView.findViewById(R.id.desc)
        var nDeleteButton: TextView = itemView.findViewById(R.id.undelete)
        var nUpdateButton: Button = itemView.findViewById(R.id.nuedit)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.need_summary_item, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val medicine = list[position]
        holder.nName.text = medicine.medicineName
        holder.nWeight.text = medicine.medicineWeight
        holder.nQty.text = medicine.quantity.toString()
        holder.nDesc.text = medicine.description

        holder.nDeleteButton.setOnClickListener {
            onDelete(medicine)
        }

        holder.nUpdateButton.setOnClickListener {
            val intent = Intent(context, EditNeedMedicine::class.java)
            intent.putExtra("medicine_id", medicine.medicineID)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}
