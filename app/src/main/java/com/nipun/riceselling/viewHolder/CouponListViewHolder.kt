package com.nipun.riceselling.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nipun.riceselling.R

class CouponListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var title: TextView = itemView.findViewById<TextView>(R.id.tvTitle)
    var code: TextView = itemView.findViewById<TextView>(R.id.codeTv)


}