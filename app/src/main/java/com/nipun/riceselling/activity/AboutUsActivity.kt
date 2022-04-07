package com.nipun.riceselling.activity

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.lifecycle.ViewModelProvider
import com.nipun.riceselling.R
import com.nipun.riceselling.utils.BaseActivity
import com.nipun.riceselling.viewModel.ConfigViewModel
import com.nipun.riceselling.viewModel.ConfigViewModelFactory
import com.nipun.riceselling.viewModel.UserInfoViewModel
import com.nipun.riceselling.viewModel.UserInfoViewModelFactory
import kotlinx.android.synthetic.main.activity_about_us.*
import kotlinx.android.synthetic.main.activity_myprofile.*
import kotlinx.android.synthetic.main.activity_myprofile.toolbar
import kotlinx.android.synthetic.main.activity_privacy.*
import kotlinx.android.synthetic.main.include_offer_toolbar.view.*

class AboutUsActivity : BaseActivity() {
    private val configViewModel: ConfigViewModel by lazy {
        val viewModelProviderFactory =
            ConfigViewModelFactory(
                this
            )
        ViewModelProvider(
            this,
            viewModelProviderFactory
        )[ConfigViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)
        initView()
        configApiCall()
    }

    private fun configApiCall() {
        configViewModel.configApi(this).observe(this,{

                txtAboutUs.text = Html.fromHtml(it.about_us)

        })
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        toolbar.textView6.text = "About Us"
        toolbar.imageView4.setOnClickListener {
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}