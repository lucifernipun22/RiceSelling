package com.nipun.riceselling.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nipun.riceselling.R
import com.nipun.riceselling.model.CouponModel
import com.nipun.riceselling.viewHolder.CouponListViewHolder

class CouponListAdapter(var couponModel: CouponModel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var layout: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_offer_list, parent, false)
        return CouponListViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var coupon = couponModel[position]
        if (holder is CouponListViewHolder) {
            holder.title.text = coupon.title
            holder.code.text = coupon.code

        }
    }

    override fun getItemCount(): Int {
        return couponModel.size

    }
}