package com.nipun.riceselling.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.nipun.riceselling.R
import com.nipun.riceselling.viewHolder.SliderViewHolder

class SlideAdapter(var list: List<String>, var context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var selectedItem =0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var layout: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_slide_layout, parent, false)
        return SliderViewHolder(layout)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SliderViewHolder) {
            holder.title.text = list[holder.adapterPosition]
            if (selectedItem == position) {
                holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context,R.color.primaryColor))
                holder.title.setTextColor(ContextCompat.getColor(context,R.color.white))
            }else{
                holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context,R.color.md_blue_50))
                holder.title.setTextColor(ContextCompat.getColor(context,R.color.md_grey_500))
            }
            holder.cardView.setOnClickListener {
                val previousItem = selectedItem
                selectedItem = position
                notifyItemChanged(previousItem)
                notifyItemChanged(position)
            }

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}