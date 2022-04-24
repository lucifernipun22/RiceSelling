package com.nipun.riceselling.viewHolder

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.nipun.riceselling.R

class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var title: TextView = itemView.findViewById<TextView>(R.id.title)
    var cardView: CardView = itemView.findViewById(R.id.cardView)

}