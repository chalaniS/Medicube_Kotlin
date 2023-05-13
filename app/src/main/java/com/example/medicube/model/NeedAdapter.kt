package com.example.medicube.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medicube.R

class NeedAdapter(
    private val context: Context,
    private val needList: List<NeedData>,
    private val onDelete: ((NeedData) -> Unit)? = null,
    private val onUpdate: ((NeedData) -> Unit)? = null
) : RecyclerView.Adapter<NeedAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_need_summary_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = needList[position]

        holder.mediname.text = currentItem.medicineName
        holder.weight.text = currentItem.weight
        holder.mQty.text = currentItem.quantity
        holder.mDesc.text = currentItem.description

        holder.mDeleteButton?.setOnClickListener {
            onDelete?.invoke(currentItem)
        }

        holder.mUpdateButton?.setOnClickListener {
            onUpdate?.invoke(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return needList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mediname: TextView = itemView.findViewById(R.id.mediname)
        val weight: TextView = itemView.findViewById(R.id.weight)
        val mQty: TextView = itemView.findViewById(R.id.qty)
        val mDesc: TextView = itemView.findViewById(R.id.desc)
        val mDeleteButton: Button? = itemView.findViewById(R.id.undelete)
        val mUpdateButton: Button? = itemView.findViewById(R.id.nuedit)

        init {
            mDeleteButton?.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onDelete?.invoke(needList[adapterPosition])
                }
            }

            mUpdateButton?.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onUpdate?.invoke(needList[adapterPosition])
                }
            }
        }
    }
}
