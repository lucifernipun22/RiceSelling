package com.nipun.riceselling.activity

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.se.omapi.Session
import android.text.Html
import androidx.annotation.RequiresApi
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModelProvider
import com.nipun.riceselling.R
import com.nipun.riceselling.SessionManager
import com.nipun.riceselling.utils.BaseActivity
import com.nipun.riceselling.viewModel.ConfigViewModel
import com.nipun.riceselling.viewModel.ConfigViewModelFactory
import kotlinx.android.synthetic.main.activity_myprofile.*
import kotlinx.android.synthetic.main.activity_myprofile.toolbar
import kotlinx.android.synthetic.main.activity_terms_condiiton.*
import kotlinx.android.synthetic.main.include_offer_toolbar.view.*

class TermsConditionActivity : BaseActivity() {
    private var sessionManager:  SessionManager? = null
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
        setContentView(R.layout.activity_terms_condiiton)
        sessionManager = SessionManager(this)
        initView()
        configApiCall()
    }

    private fun configApiCall() {
        configViewModel.configApi(this).observe(this,{
            textTerms.text = Html.fromHtml(it.terms_and_conditions )
        })
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        toolbar.textView6.text = "Terms & Condition"
        toolbar.imageView4.setOnClickListener {
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}