package com.nipun.riceselling.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.nipun.riceselling.BaseFragment
import com.nipun.riceselling.R
import com.nipun.riceselling.activity.NotificationActivity
import com.nipun.riceselling.adapter.MyCustomPagerAdapter
import com.nipun.riceselling.viewModel.BannerViewModel
import com.nipun.riceselling.viewModel.BannerViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.include_home_toolbar.*

class HomeFragment : BaseFragment() {
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
        bannerApiCall()
    }

    private fun bannerApiCall() {
        bannerViewModel.bannerApi(baseActivity?.baseContext!!).observe(this,{
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