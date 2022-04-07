package com.nipun.riceselling.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nipun.riceselling.R
import com.nipun.riceselling.model.NotificationListModel
import com.nipun.riceselling.utils.Constants
import com.nipun.riceselling.viewHolder.NotificationViewHolder
import java.text.SimpleDateFormat

class NotificationAdapter(var notificationListModel: NotificationListModel, var context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var layout: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_notification, parent, false)
        return NotificationViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var coupon = notificationListModel[position]
        if (holder is NotificationViewHolder) {
            holder.title.text = coupon.description
            holder.code.text = coupon.title
            /*var date = coupon.created_at
            var newDate : DateTime =
            holder.tvTime.text = SimpleDateFormat("dd-MM-yyyy hh:mm a").format(newDate)*/
            Glide.with(context)
                .load(Constants.API_BASE_URL + "storage/app/public/notification/" + "" + coupon.image)
                .placeholder(R.drawable.app_logo).into(holder.ivNoti)


        }
    }

    override fun getItemCount(): Int {
        return notificationListModel.size

    }
}