package com.nipun.riceselling.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nipun.riceselling.R
import com.nipun.riceselling.model.Product
import com.nipun.riceselling.utils.Constants
import com.nipun.riceselling.viewHolder.PopularViewHolder

class PopularAdapter(var popularProductsModel: ArrayList<Product>, var context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var layout: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_top_sales, parent, false)
        return PopularViewHolder(layout)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var coupon = popularProductsModel[position]
        if (holder is PopularViewHolder) {
            holder.title.text = "${coupon.capacity}"+" "+"${coupon.unit}"
            Glide.with(context)
                .load(Constants.API_BASE_URL + "storage/app/public/product/" + "" + popularProductsModel[position].image[0])
                .placeholder(R.drawable.empty).into(holder.imageView7)
            holder.tvOffer.text = coupon.name

        }
    }

    override fun getItemCount(): Int {
        return popularProductsModel.size

    }
}