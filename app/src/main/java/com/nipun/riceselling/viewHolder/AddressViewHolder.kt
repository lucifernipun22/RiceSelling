package com.nipun.riceselling.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nipun.riceselling.R

class AddressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {

    var radioButton: RadioButton = itemView.findViewById(R.id.radioButton)
    var tvAddress: TextView = itemView.findViewById(R.id.tvAddress)
    var cardView = itemView.findViewById<ImageView>(R.id.imageView6)


}