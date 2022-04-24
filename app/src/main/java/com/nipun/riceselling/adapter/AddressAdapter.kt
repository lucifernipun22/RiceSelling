package com.nipun.riceselling.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nipun.riceselling.R
import com.nipun.riceselling.model.AddressModel
import com.nipun.riceselling.viewHolder.AddressViewHolder


class AddressAdapter(var categoryListModel: AddressModel, var context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var selectedItem =0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var layout: View =
            LayoutInflater.from(parent.context).inflate(R.layout.include_address, parent, false)
        return AddressViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var coupon = categoryListModel[position]
        if (holder is AddressViewHolder) {
            if (selectedItem == position) {
                holder.radioButton.isChecked = true
            }else{
                holder.radioButton.isChecked = false
            }
            holder.cardView.setOnClickListener {
                val previousItem = selectedItem
                selectedItem = position
                notifyItemChanged(previousItem)
                notifyItemChanged(position)
            }
            holder.radioButton.setOnClickListener {
                val previousItem = selectedItem
                selectedItem = position
                notifyItemChanged(previousItem)
                notifyItemChanged(position)
            }
            holder.tvAddress.setOnClickListener {
                val previousItem = selectedItem
                selectedItem = position
                notifyItemChanged(previousItem)
                notifyItemChanged(position)
            }
            holder.tvAddress.text = coupon.address
        }
    }

    override fun getItemCount(): Int {
        return categoryListModel.size

    }
}