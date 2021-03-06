package com.nipun.riceselling.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nipun.riceselling.BaseFragment
import com.nipun.riceselling.R
import com.nipun.riceselling.SessionManager
import com.nipun.riceselling.adapter.CouponListAdapter
import com.nipun.riceselling.viewModel.CouponViewModel
import com.nipun.riceselling.viewModel.CouponViewModelFactory
import kotlinx.android.synthetic.main.activity_category_product.*
import kotlinx.android.synthetic.main.fragment_offer.*
import kotlinx.android.synthetic.main.fragment_offer.empty
import kotlinx.android.synthetic.main.fragment_offer.toolbar
import kotlinx.android.synthetic.main.include_offer_toolbar.view.*
import kotlinx.android.synthetic.main.item_empty_list.view.*


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

    @SuppressLint("ResourceAsColor")
    private fun callCouponApi() {
        couponViewModel.couponListApi(
            sessionManager!!.getStringData("token")!!,
            baseActivity!!.baseContext
        ).observe(this, {
            if (it.size > 0) {
                empty.visibility = View.GONE
                recyclerCoupon.visibility = View.VISIBLE
                couponAdapter = CouponListAdapter(it)
                recyclerCoupon.adapter = couponAdapter
            } else {
                empty.visibility = View.VISIBLE
                empty.rootView.ivEmpty.setColorFilter(ContextCompat.getColor(baseActivity?.baseContext!!, R.color.primaryColor), android.graphics.PorterDuff.Mode.MULTIPLY);
                empty.rootView.ivEmpty.setImageResource(R.drawable.ic_coupon)
                empty.rootView.tvEmpty.text  = "No Coupon Code"
                recyclerCoupon.visibility = View.GONE
            }
        })
    }


}