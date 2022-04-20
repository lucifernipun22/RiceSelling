package com.nipun.riceselling.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.nipun.riceselling.R;
import com.nipun.riceselling.activity.ProductDetailActivity;
import com.nipun.riceselling.model.ProductDetailModel;
import com.nipun.riceselling.utils.Constants;

import java.util.List;

public class MyCustomPagerAdapter2 extends PagerAdapter {
        Context context;
        ProductDetailModel imageList;
        LayoutInflater layoutInflater;

        public MyCustomPagerAdapter2(Context context, ProductDetailModel bannerDatumList) {
            this.context = context;
            this.imageList = bannerDatumList;
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public int getCount() {
            return imageList.getImage().size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View itemView = layoutInflater.inflate(R.layout.item_image, container, false);
            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
            Glide.with(context).load(Constants.API_BASE_URL + "storage/app/public/product/" + imageList.getImage().get(position)).placeholder(R.drawable.empty).into(imageView);
            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }