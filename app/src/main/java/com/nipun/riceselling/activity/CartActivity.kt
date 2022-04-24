package com.nipun.riceselling.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.gson.Gson
import com.nipun.riceselling.DatabaseHelper
import com.nipun.riceselling.R
import com.nipun.riceselling.SessionManager
import com.nipun.riceselling.adapter.ItemAdp
import com.nipun.riceselling.model.MyCart
import com.nipun.riceselling.utils.BaseActivity
import com.nipun.riceselling.utils.motionToast.MotionToast
import com.nipun.riceselling.utils.motionToast.MotionToastStyle
import com.nipun.riceselling.viewModel.ApplyCodeViewModel
import com.nipun.riceselling.viewModel.ApplyCodeViewModelFactory
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.include_offer_toolbar.view.*
import java.text.DecimalFormat
import java.util.*


class CartActivity : BaseActivity() {
    var databaseHelper: DatabaseHelper? = null
    var myCarts: ArrayList<MyCart>? = null
    var gridLayoutManager: StaggeredGridLayoutManager? = null
    var total = 0.0
    var sessionManager: SessionManager? = null
    private val applyCodeViewModel: ApplyCodeViewModel by lazy {
        val viewModelProviderFactory =
            ApplyCodeViewModelFactory(
                this
            )
        ViewModelProvider(
            this,
            viewModelProviderFactory
        )[ApplyCodeViewModel::class.java]
    }
    var applied: Boolean = false
    var discountValue = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        toolbar.textView6.text = "Shopping Bag"
        toolbar.imageView4.setOnClickListener {
            finish()
        }

        databaseHelper = DatabaseHelper(this)
        sessionManager = SessionManager(this)
        myCarts = ArrayList<MyCart>()
        gridLayoutManager = StaggeredGridLayoutManager(1, 1)
        recyclerView.layoutManager = gridLayoutManager
        val res = databaseHelper!!.allData
        if (res.count == 0) {
            lvl_notfound.visibility = View.VISIBLE
            txt_notfound.text = "Cart Empty"
            nested.visibility = View.GONE
            btn_checkout.visibility = View.GONE
        }
        while (res.moveToNext()) {
            val rModel = MyCart()
            rModel.setId(res.getString(0))
            rModel.setPid(res.getString(1))
            rModel.setImage(res.getString(2))
            rModel.setTitle(res.getString(3))
            rModel.setWeight(res.getString(4))
            rModel.setCost(res.getString(5))
            rModel.setQty(res.getString(6))
            rModel.setDiscount(res.getInt(7))
            myCarts!!.add(rModel)
        }

        val itemAdp = ItemAdp(this, myCarts)
        recyclerView.adapter = itemAdp
        updateItem()
    }

    @SuppressLint("SetTextI18n")
    fun updateItem() {
        val res = databaseHelper!!.allData
        var totalRs = 0.0
        var ress = 0.0
        var totalItem = 0
        var subtotal = 0.0
        if (res.count == 0) {
            lvl_notfound.visibility = View.VISIBLE
            txt_notfound.text = "Cart Empty"
            nested.visibility = View.GONE
        }
        while (res.moveToNext()) {
            val rModel = MyCart()
            rModel.setCost(res.getString(5))
            rModel.setQty(res.getString(6))
            rModel.setDiscount(res.getInt(7))
            ress = res.getString(5).toDouble() * rModel.getDiscount() / 100
            ress = res.getString(5).toDouble() - ress
            val temp = res.getString(6).toInt() * ress
            totalRs += temp
            totalItem += res.getString(6).toInt()
        }
        total = totalRs.toString().toDouble()
        tv_item_value.text = "₹ " + DecimalFormat("##.##").format(totalRs).toDouble()
        totalAmount.text = "₹ " + (DecimalFormat("##.##").format(
            totalRs - discountValue
        )).toDouble()

        textView11.setOnClickListener {
            if (editText.text.toString().isNotEmpty()) {
                applyCodeViewModel.bannerApi(
                    editText.text.toString(),
                    this,
                    sessionManager?.getStringData("token")!!
                ).observe(this, {
                    discountValue = it.discount.toDouble()
                    if (it.min_purchase.toDouble() < totalRs) {
                        MotionToast.createToast(
                            this,
                            "Failed",
                            "Minimum purchase should be ₹ ${it.min_purchase}",
                            MotionToastStyle.ERROR,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(this, R.font.helvetica_regular)
                        )
                    } else if (applied) {
                        MotionToast.createToast(
                            this,
                            "",
                            "Coupon Already applied",
                            MotionToastStyle.ERROR,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(this, R.font.helvetica_regular)
                        )
                    } else {

                        tv_coupon_discount_value.text =
                            "- ₹ " + DecimalFormat("##.##").format(it.discount.toDouble())
                                .toDouble()
                        totalAmount.text = "₹ " + (DecimalFormat("##.##").format(
                            totalRs - it.discount.toDouble()
                        )).toDouble()
                        applied = true
                    }
                })
            } else {
                MotionToast.createToast(
                    this,
                    "Failed",
                    "Please enter coupon code",
                    MotionToastStyle.ERROR,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(this, R.font.helvetica_regular)
                )
            }
        }

        btn_checkout.setOnClickListener {
            val intent = Intent(this,CheckOutActivity::class.java)
            val gson = Gson()
            val jsonCart: String = gson.toJson(myCarts)
            intent.putExtra("cart", jsonCart);
            intent.putExtra("total",totalRs.toString())
            intent.putExtra("type","delivery")
            intent.putExtra("discount",discountValue.toString())
            intent.putExtra("coupon",editText.text.toString())
            startActivity(intent)
        }

    }
}