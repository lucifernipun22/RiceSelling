package com.nipun.riceselling.fragment

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.nipun.riceselling.BaseFragment
import com.nipun.riceselling.DatabaseHelper
import com.nipun.riceselling.R
import com.nipun.riceselling.activity.CartActivity
import com.nipun.riceselling.activity.NotificationActivity
import com.nipun.riceselling.adapter.CategoryAdapter
import com.nipun.riceselling.adapter.MyCustomPagerAdapter
import com.nipun.riceselling.adapter.PopularAdapter
import com.nipun.riceselling.viewModel.*
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.tabview
import kotlinx.android.synthetic.main.fragment_home.toolbar
import kotlinx.android.synthetic.main.fragment_home.viewPager
import kotlinx.android.synthetic.main.include_home_toolbar.*
import kotlinx.android.synthetic.main.include_toolbar_product.view.*

class HomeFragment : BaseFragment() {
    private var categoryAdapter: CategoryAdapter? = null
    private var popularAdapter: PopularAdapter? = null

    companion object {
        lateinit var txt_countcard: TextView
    }

    var databaseHelper: DatabaseHelper? = null

    private val bannerViewModel: BannerViewModel by lazy {
        val viewModelProviderFactory =
            BannerViewModelFactory(
                baseActivity!!
            )
        ViewModelProvider(
            this,
            viewModelProviderFactory
        )[BannerViewModel::class.java]
    }

    private val categoryViewModel: CategoryViewModel by lazy {
        val viewModelProviderFactory =
            CategoryViewModelFactory(
                baseActivity!!
            )
        ViewModelProvider(
            this,
            viewModelProviderFactory
        )[CategoryViewModel::class.java]
    }

    private val popularProductViewModel: PopularProductViewModel by lazy {
        val viewModelProviderFactory =
            PopularProductViewModelFactory(
                baseActivity!!
            )
        ViewModelProvider(
            this,
            viewModelProviderFactory
        )[PopularProductViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ivNotification.setOnClickListener {
            val intent = Intent(baseActivity?.baseContext, NotificationActivity::class.java)
            startActivity(intent)
        }
        initView()
        bannerApiCall()
        categoryApi()
        popularProductAPI()
    }

    private fun popularProductAPI() {
        popularProductViewModel.bannerApi(baseActivity?.baseContext!!).observe(this, {
            popularAdapter = PopularAdapter(it.products, baseActivity?.baseContext!!)
            recyclerViewPopular.adapter = popularAdapter
        })
    }

    private fun initView() {
        databaseHelper = DatabaseHelper(baseActivity?.baseContext)
        txt_countcard = toolbar.findViewById(R.id.txt_tcount)
        val res: Cursor = databaseHelper!!.allData
        if (res.count == 0) {
            txt_countcard.text = "0"
        } else {
            txt_countcard.text = "" + res.count
        }
        recyclerViewCategory.layoutManager = GridLayoutManager(baseActivity?.baseContext, 3)
        recyclerViewCategory.isNestedScrollingEnabled = false

        recyclerViewPopular.layoutManager =
            LinearLayoutManager(baseActivity?.baseContext, LinearLayoutManager.VERTICAL, false)
        recyclerViewPopular.isNestedScrollingEnabled = false
        toolbar.lvl_cart.setOnClickListener {
            val intent = Intent(baseActivity?.baseContext, CartActivity::class.java)
            startActivity(intent)
        }
    }

    private fun categoryApi() {
        categoryViewModel.categoryApi(baseActivity?.baseContext!!).observe(this, {
            categoryAdapter = CategoryAdapter(it, baseActivity?.baseContext!!)
            recyclerViewCategory.adapter = categoryAdapter
        })
    }

    private fun bannerApiCall() {
        bannerViewModel.bannerApi(baseActivity?.baseContext!!).observe(this, {
            val myCustomPagerAdapter =
                MyCustomPagerAdapter(
                    baseActivity?.baseContext!!,
                    it
                )
            viewPager.adapter = myCustomPagerAdapter
            viewPager.startAutoScroll()
            viewPager.interval = 3000
            viewPager.isCycle = true
            viewPager.isStopScrollWhenTouch = true
            tabview.setupWithViewPager(viewPager, true)
        })
    }

}