package com.nipun.riceselling.activity

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.nipun.riceselling.R
import com.nipun.riceselling.SessionManager
import com.nipun.riceselling.SessionManager.Companion.login
import com.nipun.riceselling.utils.BaseActivity
import com.nipun.riceselling.viewModel.LoginViewModel
import com.nipun.riceselling.viewModel.LoginViewModelFactory
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    var baseActivity = BaseActivity()
    var sessionManager : SessionManager? = null
    val loginViewModel: LoginViewModel by lazy {
        val viewModelProviderFactory =
            LoginViewModelFactory(
                this
            )
        ViewModelProvider(
            this,
            viewModelProviderFactory
        )[LoginViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
    }

    private fun initView() {

        sessionManager = SessionManager(this)
        imageView.setOnClickListener {
            loginApi()
        }

    }

    private fun loginApi() {
        loginViewModel.loginApi().observe(this, {
            sessionManager?.setBooleanData(login, true)
            var intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        })
    }


}