package com.nipun.riceselling.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.nipun.riceselling.R

class PopularViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var title: TextView = itemView.findViewById<TextView>(R.id.tvTitle)
    var tvOffer: TextView = itemView.findViewById<TextView>(R.id.offerTv)
    var imageView7: ImageView = itemView.findViewById(R.id.imageView7)
    var clMain: ConstraintLayout = itemView.findViewById(R.id.clMain)
    var textView10 : TextView = itemView.findViewById(R.id.textView10)
    var imageView5: TextView = itemView.findViewById(R.id.imageView5)
    var imageView6: TextView = itemView.findViewById(R.id.imageView8)



}