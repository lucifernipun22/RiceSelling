package com.nipun.riceselling.activity

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProvider
import com.nipun.riceselling.R
import com.nipun.riceselling.SessionManager
import com.nipun.riceselling.utils.BaseActivity
import com.nipun.riceselling.utils.Constants
import com.nipun.riceselling.viewModel.UserInfoViewModel
import com.nipun.riceselling.viewModel.UserInfoViewModelFactory
import kotlinx.android.synthetic.main.activity_payment.*
import kotlinx.android.synthetic.main.include_offer_toolbar.view.*


class PaymentActivity : BaseActivity() {
    var baseActivity = BaseActivity()
    private val userInfoViewModel: UserInfoViewModel by lazy {
        val viewModelProviderFactory =
            UserInfoViewModelFactory(
                this
            )
        ViewModelProvider(
            this,
            viewModelProviderFactory
        )[UserInfoViewModel::class.java]
    }
    private var sessionManager : SessionManager? = null

    var orderId =0

    private class HelloWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            return false
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        sessionManager = SessionManager(this)
        initView()
    }

    private fun initView() {
        userInfoViewModel.couponListApi(sessionManager!!.getStringData("token").toString(),this).observe(this,{
            webView.settings.javaScriptEnabled = true
            webView.settings.loadWithOverviewMode = true
            webView.settings.useWideViewPort = true
            webView.settings.builtInZoomControls = true
            webView.settings.pluginState = WebSettings.PluginState.ON
            webView.webViewClient = HelloWebViewClient()
            webView.loadUrl(Constants.API_BASE_URL+"admin/payment-mobile?customer_id=${it.id}"+"&order_id=${orderId}")

        })
        orderId = intent.getIntExtra("orderId",0)
        toolbar.imageView4.setOnClickListener {
            finish()
        }
        toolbar.textView6.text = "Payment"
        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                baseActivity.startProgress()

            }

            override fun onPageFinished(view: WebView, url: String) {
                Log.e("url",url)
                baseActivity.stopProgress()
                if(url.contains("success") && url.contains(Constants.API_BASE_URL)){
                    val intent = Intent(this@PaymentActivity,OrderSuccessFullyActivity::class.java)
                    startActivity(intent)
                }else if(url.contains("fail") && url.contains(Constants.API_BASE_URL)){
                    val intent = Intent(this@PaymentActivity,OrderSuccessFullyActivity::class.java)
                    startActivity(intent)
                }else if(url.contains("cancel") && url.contains(Constants.API_BASE_URL)){
                    val intent = Intent(this@PaymentActivity,OrderSuccessFullyActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}