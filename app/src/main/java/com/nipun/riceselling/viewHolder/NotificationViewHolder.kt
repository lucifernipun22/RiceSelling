package com.nipun.riceselling.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nipun.riceselling.R

class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var title: TextView = itemView.findViewById<TextView>(R.id.tvTitle)
    var code: TextView = itemView.findViewById<TextView>(R.id.offerTv)
    var tvTime: TextView = itemView.findViewById(R.id.Tvtime)
    var ivNoti : ImageView = itemView.findViewById(R.id.ivNoti)


}