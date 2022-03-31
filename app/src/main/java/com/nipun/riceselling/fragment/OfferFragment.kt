package com.nipun.riceselling.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nipun.riceselling.BaseFragment
import com.nipun.riceselling.R
import com.nipun.riceselling.SessionManager
import com.nipun.riceselling.adapter.CouponListAdapter
import com.nipun.riceselling.viewModel.CouponViewModel
import com.nipun.riceselling.viewModel.CouponViewModelFactory
import kotlinx.android.synthetic.main.fragment_offer.*
import kotlinx.android.synthetic.main.include_offer_toolbar.view.*


class OfferFragment : BaseFragment() {

    private var sessionManager: SessionManager? = null
    private var couponAdapter: CouponListAdapter? = null
    private val couponViewModel: CouponViewModel by lazy {
        val viewModelProviderFactory =
            CouponViewModelFactory(
                baseActivity!!
            )
        ViewModelProvider(
            this,
            viewModelProviderFactory
        )[CouponViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManager = SessionManager(baseActivity)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_offer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initView() {
        recyclerCoupon.layoutManager = LinearLayoutManager(baseActivity)
        recyclerCoupon.isNestedScrollingEnabled = false
        toolbar.textView6.text = "Offers"
        toolbar.imageView4.visibility = View.GONE
        callCouponApi()
    }

    private fun callCouponApi() {
        couponViewModel.couponListApi(sessionManager!!.getStringData("token")!!,baseActivity!!.baseContext).observe(this,{
            couponAdapter = CouponListAdapter(it)
            recyclerCoupon.adapter  = couponAdapter
        })
    }


}