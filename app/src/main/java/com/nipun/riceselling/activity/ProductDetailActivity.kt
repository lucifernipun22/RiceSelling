package com.nipun.riceselling.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.nipun.riceselling.DatabaseHelper
import com.nipun.riceselling.R
import com.nipun.riceselling.SessionManager
import com.nipun.riceselling.adapter.MyCustomPagerAdapter2
import com.nipun.riceselling.model.MyCart
import com.nipun.riceselling.utils.BaseActivity
import com.nipun.riceselling.viewModel.ProductDetailViewModel
import com.nipun.riceselling.viewModel.ProductDetailViewModelFactory
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.include_toolbar_product.view.*
import java.util.*

class ProductDetailActivity : BaseActivity() {
    var id: String? = null
    var databaseHelper: DatabaseHelper? = null
    var sessionManager: SessionManager? = null
    val count = intArrayOf(0)
    var myCart: MyCart = MyCart()

    private val productDetailViewModel: ProductDetailViewModel by lazy {
        val viewModelProviderFactory =
            ProductDetailViewModelFactory(
                this
            )
        ViewModelProvider(
            this,
            viewModelProviderFactory
        )[ProductDetailViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        sessionManager = SessionManager(this)
        databaseHelper = DatabaseHelper(this@ProductDetailActivity)
        initView()
        updateItem()
        productDetailApi()
    }

    private fun productDetailApi() {
        productDetailViewModel.productApi(id!!, this).observe(this, {
            if (it.image.isNotEmpty()
            ) {
                tabview.setupWithViewPager(viewPager, true)
            }
            val myCustomPagerAdapter = MyCustomPagerAdapter2(this, it)
            viewPager.adapter = myCustomPagerAdapter
            txt_title.text = it.name
            txt_price.text = it.price.toString()
            txt_pcs.text = it.capacity.toString() + " " + it.unit
            tv_description.text = it.description
            myCart.setPid(it.id.toString().toString())
            myCart.setImage(it.image[0].toString())
            myCart.setTitle(it.name.toString())
            myCart.setWeight(it.capacity.toString() +" " +it.unit.toString())
            myCart.setCost(it.price.toString())
            myCart.setDiscount(it.discount)

            val qrt: Int = databaseHelper!!.getCard(myCart.getPid(), myCart.getCost())
            if (qrt != -1) {
                count[0] = qrt
                textView10.text = count[0].toString()
                tv_price.text = "₹ "+(count[0] *  txt_price.text.toString().toInt()).toString()
                imageView6.visibility = View.VISIBLE
                btn_addtocart.setBackgroundResource(R.drawable.ic_rectangle_green)
            } else {
                tv_price.text ="₹ 0"
                btn_addtocart.setBackgroundResource(R.drawable.ic_rectangle_light_green)
            }
            imageView5.setOnClickListener {
                count[0] = textView10.text.toString().toInt()
                imageView6.visibility = View.VISIBLE
                btn_addtocart.setBackgroundResource(R.drawable.ic_rectangle_green)
                count[0] = count[0] + 1
                textView10.text = count[0].toString()
                myCart.setQty(count[0].toString())
                databaseHelper!!.insertData(myCart)
                tv_price.text = "₹ "+(count[0] *  txt_price.text.toString().toInt()).toString()
                updateItem()

            }

            imageView6.setOnClickListener {
                count[0] = textView10.text.toString().toInt()

                count[0] = count[0] - 1
                if (count[0] <= 0) {
                    //imageView6.visibility = View.GONE
                    btn_addtocart.setBackgroundResource(R.drawable.ic_rectangle_light_green)
                    textView10.text = "0"
                    databaseHelper!!.deleteRData(myCart.getPid(), myCart.getCost())

                } else {
                    imageView6.visibility = View.VISIBLE
                    btn_addtocart.setBackgroundResource(R.drawable.ic_rectangle_green)
                    textView10.visibility = View.VISIBLE
                    textView10.text = "" + count[0]
                    myCart.setQty(count[0].toString())
                    databaseHelper!!.insertData(myCart)
                }
                tv_price.text = "₹ "+(count[0] *  txt_price.text.toString().toInt()).toString()
                updateItem()
            }
        })
    }

    private fun initView() {
        id = intent.getStringExtra("id")
        toolbar.lvl_cart.setOnClickListener {
            val intent = Intent(this,CartActivity::class.java)
            startActivity(intent)
        }
    }

    private fun updateItem() {
        val res = databaseHelper!!.allData
        if (res.count == 0) {
            toolbar.rootView.txt_tcount.text = "0"
        } else {
            toolbar.rootView.txt_tcount.text = "" + res.count
        }
    }
}