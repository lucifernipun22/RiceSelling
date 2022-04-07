package com.nipun.riceselling.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.nipun.riceselling.R
import com.nipun.riceselling.model.BannerModel
import com.nipun.riceselling.utils.Constants

class MyCustomPagerAdapter(var context: Context, bannerDatumList: BannerModel) :
    PagerAdapter() {
    var bannerDatumList: BannerModel = bannerDatumList
    var layoutInflater: LayoutInflater
    override fun getCount(): Int {
        return bannerDatumList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView: View = layoutInflater.inflate(R.layout.item_banner, container, false)
        val imageView = itemView.findViewById<View>(R.id.imageView) as ImageView
        Glide.with(context)
            .load(Constants.API_BASE_URL+"storage/app/public/banner/" + "" + bannerDatumList[position].image)
            .placeholder(R.drawable.empty).into(imageView)
        container.addView(itemView)
        imageView.setOnClickListener {

        }
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }

    init {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }
}