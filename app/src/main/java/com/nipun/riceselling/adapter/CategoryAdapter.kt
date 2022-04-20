package com.nipun.riceselling.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nipun.riceselling.R
import com.nipun.riceselling.activity.CategoryProductActivity
import com.nipun.riceselling.model.CategoryListModel
import com.nipun.riceselling.utils.Constants
import com.nipun.riceselling.viewHolder.CategoryViewHolder

class CategoryAdapter(var categoryListModel: CategoryListModel, var context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var layout: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var coupon = categoryListModel[position]
        if (holder is CategoryViewHolder) {
            holder.title.text = coupon.name
            Glide.with(context)
                .load(Constants.API_BASE_URL + "storage/app/public/category/" + "" + categoryListModel[position].image)
                .placeholder(R.drawable.empty).into(holder.image)
            holder.cardView.setOnClickListener {
                val intent = Intent(context, CategoryProductActivity::class.java)
                intent.putExtra("id", categoryListModel[position].id.toString())
                intent.putExtra("name", categoryListModel[position].name.toString())
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }

        }
    }

    override fun getItemCount(): Int {
        return categoryListModel.size

    }
}