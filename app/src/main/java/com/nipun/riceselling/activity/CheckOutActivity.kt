package com.nipun.riceselling.activity

import android.content.Intent
import android.os.Bundle
import com.nipun.riceselling.R
import com.nipun.riceselling.utils.BaseActivity
import kotlinx.android.synthetic.main.activity_add_address.*
import kotlinx.android.synthetic.main.activity_check_out.*
import kotlinx.android.synthetic.main.activity_check_out.toolbar
import kotlinx.android.synthetic.main.include_offer_toolbar.view.*

class CheckOutActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)

        initView()
    }

    private fun initView() {
        toolbar.rootView.textView6.text = "Checkout"
        toolbar.rootView.imageView4.setOnClickListener {
            finish()
        }
        addBtn.setOnClickListener {
            var intent = Intent(this, AddAddressActivity::class.java)
            startActivity(intent)
        }
    }
}