package com.nipun.riceselling.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nipun.riceselling.DatabaseHelper
import com.nipun.riceselling.R
import com.nipun.riceselling.activity.ProductDetailActivity
import com.nipun.riceselling.model.CategoryProductModel
import com.nipun.riceselling.model.MyCart
import com.nipun.riceselling.utils.Constants
import com.nipun.riceselling.viewHolder.PopularViewHolder
import kotlinx.android.synthetic.main.activity_product_detail.*

class CategoryProductAdapter(var popularProductsModel: CategoryProductModel, var context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var databaseHelper: DatabaseHelper? = null
    private var myCart = MyCart()
    var count = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var layout: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_top_sales, parent, false)
        return PopularViewHolder(layout)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var coupon = popularProductsModel[position]
        if (holder is PopularViewHolder) {
            databaseHelper = DatabaseHelper(context)
            val qrt: Int = databaseHelper!!.getCard(
                popularProductsModel[holder.adapterPosition].id.toString(),
                popularProductsModel[holder.adapterPosition].price.toString()
            )
            if (qrt != -1) {
                holder.textView10.text = qrt.toString()
                holder.imageView6.visibility = View.VISIBLE
            } else {
                holder.textView10.text = "0"
            }
            holder.imageView5.setOnClickListener {
                myCart.setPid(popularProductsModel[holder.adapterPosition].id.toString())
                myCart.setImage(popularProductsModel[holder.adapterPosition].image[0].toString())
                myCart.setTitle(popularProductsModel[holder.adapterPosition].name.toString())
                myCart.setWeight(popularProductsModel[holder.adapterPosition].capacity.toString() + " "+ popularProductsModel[holder.adapterPosition].unit.toString())
                myCart.setCost(popularProductsModel[holder.adapterPosition].price.toString())
                myCart.setDiscount(popularProductsModel[holder.adapterPosition].discount)
                count =holder.textView10.text.toString().toInt()
                holder.imageView6.visibility = View.VISIBLE
                count += 1
                holder.textView10.text = count.toString()
                myCart.setQty(count.toString())
                databaseHelper!!.insertData(myCart)
            }
            holder.imageView6.setOnClickListener {
                count = holder.textView10.text.toString().toInt()
                myCart.setPid(popularProductsModel[holder.adapterPosition].id.toString())
                myCart.setImage(popularProductsModel[holder.adapterPosition].image[0].toString())
                myCart.setTitle(popularProductsModel[holder.adapterPosition].name.toString())
                myCart.setWeight(popularProductsModel[holder.adapterPosition].capacity.toString() + " "+ popularProductsModel[holder.adapterPosition].unit.toString())
                myCart.setCost(popularProductsModel[holder.adapterPosition].price.toString())
                myCart.setDiscount(popularProductsModel[holder.adapterPosition].discount)
                count -= 1
                if (count <= 0) {
                    holder.textView10.text = "0"
                    databaseHelper!!.deleteRData(popularProductsModel[holder.adapterPosition].id.toString(), popularProductsModel[holder.adapterPosition].price.toString())

                } else {
                    holder.imageView6.visibility = View.VISIBLE
                    holder.textView10.visibility = View.VISIBLE
                    holder.textView10.text = "" + count
                    myCart.setQty(count.toString())
                    databaseHelper!!.insertData(myCart)
                }
            }
            holder.title.text = "${coupon.capacity}" + " " + "${coupon.unit}"
            Glide.with(context)
                .load(Constants.API_BASE_URL + "admin/storage/app/public/product/" + "" + popularProductsModel[position].image[0])
                .placeholder(R.drawable.empty).into(holder.imageView7)
            holder.tvOffer.text = coupon.name
            holder.clMain.setOnClickListener {
                val intent = Intent(context, ProductDetailActivity::class.java)
                intent.putExtra("id", popularProductsModel[position].id.toString())
                context.startActivity(intent)
            }

        }
    }

    override fun getItemCount(): Int {
        return popularProductsModel.size

    }
}