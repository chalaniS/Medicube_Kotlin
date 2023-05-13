package com.example.medicube.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medicube.R
import android.content.Intent
import com.example.medicube.DeleteUpdate


class PharmacyAdapter(
    private val context: Context,
    private val list: ArrayList<PharmacyData>,
    private val onDelete: (PharmacyData) -> Unit
) : RecyclerView.Adapter<PharmacyAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var pName: TextView = itemView.findViewById(R.id.txt_pName)
        var pLisence: TextView = itemView.findViewById(R.id.txt_pLisence)
        var pLocation: TextView = itemView.findViewById(R.id.txt_pLocation)
        var pOwner: TextView = itemView.findViewById(R.id.txt_pOwner)
        var pDeleteButton: Button = itemView.findViewById(R.id.deleteBtn)
        var pUpdateButton: Button = itemView.findViewById(R.id.updateBtn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.pharmacy_item, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val pharmacy = list[position]
        holder.pName.text = pharmacy.name
        holder.pLisence.text = pharmacy.lisence
        holder.pLocation.text = pharmacy.location
        holder.pOwner.text = pharmacy.owner

        holder.pDeleteButton.setOnClickListener {
            onDelete(pharmacy)
        }

        holder.pUpdateButton.setOnClickListener {
//            val intent = Intent(context, DeleteUpdate::class.java)
//            intent.putExtra("pharmacy_id", pharmacy.id)
//            context.startActivity(intent)

            val intent = Intent(context, DeleteUpdate::class.java)
            intent.putExtra("pharmacy_id", pharmacy.id)
            context.startActivity(intent)

        }

    }


    override fun getItemCount(): Int {
        return list.size
    }
}