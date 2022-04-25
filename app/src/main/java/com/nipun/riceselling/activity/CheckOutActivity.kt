package com.nipun.riceselling.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nipun.riceselling.DatabaseHelper
import com.nipun.riceselling.R
import com.nipun.riceselling.SessionManager
import com.nipun.riceselling.adapter.AddressAdapter
import com.nipun.riceselling.model.MyCart
import com.nipun.riceselling.utils.BaseActivity
import com.nipun.riceselling.viewModel.AddressFetchViewModel
import com.nipun.riceselling.viewModel.AddressFetchViewModelFactory
import com.nipun.riceselling.viewModel.PlaceOrderViewModel
import com.nipun.riceselling.viewModel.PlaceOrderViewModelFactory
import kotlinx.android.synthetic.main.activity_check_out.*
import kotlinx.android.synthetic.main.activity_check_out.toolbar
import kotlinx.android.synthetic.main.include_offer_toolbar.view.*
import java.lang.reflect.Type


class CheckOutActivity : BaseActivity() {
    var total: String? = null
    var typee: String? = null
    var discount: String? = null
    var coupon: String? = null
    var deliveryCharges = 0.0
    var cartList: ArrayList<MyCart>? = null
    var addressPosition = 0
    var paymentMethod =""
    private var myCart = MyCart()
    private var databaseHelper: DatabaseHelper? = null

    private val fetchAddressViewModel: AddressFetchViewModel by lazy {
        val viewModelProviderFactory =
            AddressFetchViewModelFactory(
                this
            )
        ViewModelProvider(
            this,
            viewModelProviderFactory
        )[AddressFetchViewModel::class.java]
    }

    private val placeOrderViewModel: PlaceOrderViewModel by lazy {
        val viewModelProviderFactory =
            PlaceOrderViewModelFactory(
                this
            )
        ViewModelProvider(
            this,
            viewModelProviderFactory
        )[PlaceOrderViewModel::class.java]
    }
    private var sessionManager: SessionManager? = null
    private var addressAdapter: AddressAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)
        databaseHelper = DatabaseHelper(this)
        sessionManager = SessionManager(this)
        initView()
    }

    private fun initView() {

        fetchAddressViewModel.addressFetchApi(
            this,
            sessionManager!!.getStringData("token").toString()
        ).observe(this, {
            recyclerViewAdd.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
            addressAdapter = AddressAdapter(it, this)
            if (it.size > 0) {
                recyclerViewAdd.adapter = addressAdapter
                noAddress.visibility = View.GONE
                recyclerViewAdd.visibility = View.VISIBLE
            } else {
                noAddress.visibility = View.VISIBLE
                recyclerViewAdd.visibility = View.GONE
            }

        })
        total = intent.getStringExtra("total")
        typee = intent.getStringExtra("type")
        discount = intent.getStringExtra("discount")
        coupon = intent.getStringExtra("coupon")
        val cartListAsString = intent.getStringExtra("cart")
        val gson = Gson()
        val type: Type = object : TypeToken<List<MyCart?>?>() {}.type
        cartList = gson.fromJson(cartListAsString, type)
        var totalvalue = total?.toDouble()?.minus(discount!!.toDouble())
        tv_item_value.text = "₹ " + totalvalue.toString()
        toolbar.rootView.textView6.text = "Checkout"
        toolbar.rootView.imageView4.setOnClickListener {
            finish()
        }
        totalAmount.text = "₹ " + totalvalue.toString()
        addBtn.setOnClickListener {
            var intent = Intent(this, AddAddressActivity::class.java)
            startActivity(intent)
        }
        btnPlaceOrder.setOnClickListener {
            if(cashRb.isChecked||digiRb.isChecked){
                if(cashRb.isChecked){
                    paymentMethod = "cash_on_delivery"
                }else{
                    paymentMethod ="Online"
                }
                placeOrderViewModel.placeOrderApi(this,sessionManager!!.getStringData("token").toString(),cartList!!,total,typee,coupon,discount,addressPosition,paymentMethod).observe(this,{
                    if(paymentMethod =="cash_on_delivery"){
                        val intent = Intent(this,OrderSuccessFullyActivity::class.java)
                        startActivity(intent)
                    }else{
                        val intent = Intent(this,PaymentActivity::class.java)
                        intent.putExtra("orderId",it.order_id)
                        startActivity(intent)
                    }
                })

            }
        }
    }
}