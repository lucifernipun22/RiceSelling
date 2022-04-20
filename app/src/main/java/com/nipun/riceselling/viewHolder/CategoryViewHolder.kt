package com.nipun.riceselling.viewHolder

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.nipun.riceselling.R
import de.hdodenhof.circleimageview.CircleImageView

class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var title: TextView = itemView.findViewById<TextView>(R.id.tvName)
    var image: CircleImageView = itemView.findViewById<CircleImageView>(R.id.circleImageView)
    var cardView: CardView = itemView.findViewById(R.id.imageView6)


}