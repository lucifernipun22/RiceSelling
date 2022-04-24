package com.nipun.riceselling.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nipun.riceselling.R
import com.nipun.riceselling.adapter.SlideAdapter
import com.nipun.riceselling.utils.BaseActivity
import kotlinx.android.synthetic.main.activity_add_address.*
import kotlinx.android.synthetic.main.include_offer_toolbar.view.*

class AddAddressActivity : BaseActivity() {

    internal var list = listOf<String>(
        "Home",
        "Workplace",
        "Other"
    )
    var adapter: SlideAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_address)
        toolbar.rootView.textView6.text = "Add New Address"
        toolbar.rootView.imageView4.setOnClickListener {
            finish()
        }
        recyclerView2.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapter = SlideAdapter(list, this)
        recyclerView2.adapter = adapter


    }
}