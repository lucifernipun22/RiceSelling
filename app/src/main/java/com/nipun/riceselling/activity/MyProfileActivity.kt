package com.nipun.riceselling.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.nipun.riceselling.R
import com.nipun.riceselling.SessionManager
import com.nipun.riceselling.utils.BaseActivity
import com.nipun.riceselling.viewModel.UserInfoViewModel
import com.nipun.riceselling.viewModel.UserInfoViewModelFactory
import kotlinx.android.synthetic.main.activity_myprofile.*
import kotlinx.android.synthetic.main.activity_myprofile.toolbar
import kotlinx.android.synthetic.main.include_offer_toolbar.view.*

class MyProfileActivity : BaseActivity() {
    private var sessionManager: SessionManager? = null

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myprofile)

        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        sessionManager = SessionManager(this)
        toolbar.textView6.text = "My Profile"
        toolbar.imageView4.setOnClickListener {
            finish()
        }
        userInfoApi()

    }

    private fun userInfoApi() {
        userInfoViewModel.couponListApi(sessionManager?.getStringData("token")!!, this)
            .observe(this, {
                try {
                    editFname.setText(it.f_name)
                    editLName.setText(it.l_name)
                    editEmail.setText(it.email)
                    editPass.setText(it.phone)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}