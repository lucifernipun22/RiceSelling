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
import android.view.View.OnFocusChangeListener
import androidx.core.content.res.ResourcesCompat
import com.nipun.riceselling.utils.motionToast.MotionToast
import com.nipun.riceselling.utils.motionToast.MotionToastStyle


class LoginActivity : BaseActivity() {
    private var sessionManager: SessionManager? = null
    private val loginViewModel: LoginViewModel by lazy {
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

        editTextEmail.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) editTextEmail.hint = "" else editTextEmail.hint = "Email"
        }
        editTextPassword.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) editTextPassword.hint = "" else editTextPassword.hint = "Password"
        }

        loginButton.setOnClickListener {
            if(editTextEmail.text.equals("") || editTextPassword.text.equals("")){
                MotionToast.createToast(this,
                    "Error ☹",
                    "Please Enter Correct Details!",
                    MotionToastStyle.ERROR,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(this, R.font.helvetica_regular))


            }else {
                loginApi(editTextEmail.text.toString(), editTextPassword.text.toString())
            }
        }
        tvForget.setOnClickListener {
            val intent = Intent(this, ForgetPasswordActivity::class.java)
            startActivity(intent)
        }
        tvSignup.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }


    }

    private fun loginApi(editEmail: String, editPass: String) {
        loginViewModel.loginApi(editEmail, editPass,this).observe(this, {
            sessionManager?.setBooleanData(login, true)
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        })
    }


}