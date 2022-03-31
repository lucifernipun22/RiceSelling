package com.nipun.riceselling.activity

import android.content.Intent
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import com.nipun.riceselling.R
import com.nipun.riceselling.SessionManager
import com.nipun.riceselling.utils.BaseActivity
import com.nipun.riceselling.utils.motionToast.MotionToast
import com.nipun.riceselling.utils.motionToast.MotionToastStyle
import com.nipun.riceselling.viewModel.SignUpViewModel
import com.nipun.riceselling.viewModel.SignUpViewModelFactory
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity() {
    var number: String? = null
    var sessionManager: SessionManager? = null
    private val signUpViewModel: SignUpViewModel by lazy {
        val viewModelProviderFactory =
            SignUpViewModelFactory(
                this
            )
        ViewModelProvider(
            this,
            viewModelProviderFactory
        )[SignUpViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManager = SessionManager(this)
        setContentView(R.layout.activity_register)
        initView()
    }

    private fun initView() {
        number = intent.getStringExtra("number")
        loginButton.setOnClickListener {
            if (validation()) {
                callSignUpApi()
            }
        }
    }

    private fun callSignUpApi() {
        signUpViewModel.signUpApiApi(
            editFname.text.toString(),
            editLName.text.toString(),
            number!!,
            editEmail.text.toString(),
            editConfirmPass.text.toString(),
            this
        ).observe(this, {
            sessionManager?.setBooleanData(SessionManager.login, true)
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        })
    }

    private fun validation(): Boolean {
        if (editFname.text.toString().isEmpty()) {
            MotionToast.createToast(
                this,
                "Error",
                "Please Enter First Name",
                MotionToastStyle.ERROR,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(this, R.font.helvetica_regular)
            )
            return false
        }
        if (editLName.text.toString().isEmpty()) {
            MotionToast.createToast(
                this,
                "Error",
                "Please Enter Last Name",
                MotionToastStyle.ERROR,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(this, R.font.helvetica_regular)
            )
            return false
        }
        if (editEmail.text.toString().isEmpty()) {
            MotionToast.createToast(
                this,
                "Error",
                "Please Enter Valid Email",
                MotionToastStyle.ERROR,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(this, R.font.helvetica_regular)
            )
            return false
        }
        if (editPass.text.length <= 6) {
            MotionToast.createToast(
                this,
                "Error",
                "Password Must contain 6 Character",
                MotionToastStyle.ERROR,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(this, R.font.helvetica_regular)
            )
            return false
        }
        if (editConfirmPass.text.toString() != editPass.text.toString()) {
            MotionToast.createToast(
                this,
                "Error",
                "Mismatch Password",
                MotionToastStyle.ERROR,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(this, R.font.helvetica_regular)
            )
            return false
        }
        if (editPass.text.toString().isEmpty()) {
            MotionToast.createToast(
                this,
                "Error",
                "Please Enter Password",
                MotionToastStyle.ERROR,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(this, R.font.helvetica_regular)
            )
            return false
        }
        if (editConfirmPass.text.toString().isEmpty()) {
            MotionToast.createToast(
                this,
                "Error",
                "Please Enter Confirm Password",
                MotionToastStyle.ERROR,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(this, R.font.helvetica_regular)
            )
            return false
        }
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, SignUpActivity::class.java))
        finish()
    }
}