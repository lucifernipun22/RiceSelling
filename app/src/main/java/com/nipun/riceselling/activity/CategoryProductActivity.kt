package com.nipun.riceselling.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nipun.riceselling.R
import com.nipun.riceselling.adapter.CategoryProductAdapter
import com.nipun.riceselling.utils.BaseActivity
import com.nipun.riceselling.viewModel.CategoryProductViewModel
import com.nipun.riceselling.viewModel.CategoryProductViewModelFactory
import kotlinx.android.synthetic.main.activity_category_product.*
import kotlinx.android.synthetic.main.include_offer_toolbar.view.*
import kotlinx.android.synthetic.main.item_empty_list.view.*

class CategoryProductActivity : BaseActivity() {
    var id: String? = null
    var name: String? = null

    private var categoryProductAdapter: CategoryProductAdapter? = null
    private val categoryProductViewModel: CategoryProductViewModel by lazy {
        val viewModelProviderFactory =
            CategoryProductViewModelFactory(
                this
            )
        ViewModelProvider(
            this,
            viewModelProviderFactory
        )[CategoryProductViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_product)

        initView()

    }

    override fun onResume() {
        super.onResume()
        categoryProductApi()
    }

    private fun categoryProductApi() {
        categoryProductViewModel.categoryProductApi(id!!, this).observe(this, {
            if (it.size > 0) {
                empty.visibility = View.GONE
                recyclerViewCategoryProduct.visibility = View.VISIBLE
                categoryProductAdapter = CategoryProductAdapter(it, this)
                recyclerViewCategoryProduct.adapter = categoryProductAdapter
            } else {
                empty.visibility = View.VISIBLE
                empty.rootView.ivEmpty.setImageResource(R.drawable.shopping_cart)
                empty.rootView.tvEmpty.text = "No Product for ${name} Category"
                recyclerViewCategoryProduct.visibility = View.GONE
            }
        })
    }

    private fun initView() {
        id = intent.getStringExtra("id")
        name = intent.getStringExtra("name")
        toolbar.textView6.text = name
        toolbar.imageView4.setOnClickListener {
            finish()
        }
        recyclerViewCategoryProduct.layoutManager = LinearLayoutManager(this)
        recyclerViewCategoryProduct.isNestedScrollingEnabled = false

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}