package com.nipun.riceselling.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import com.nipun.riceselling.R
import com.nipun.riceselling.utils.BaseActivity
import com.nipun.riceselling.utils.motionToast.MotionToast
import com.nipun.riceselling.utils.motionToast.MotionToastStyle
import com.nipun.riceselling.viewModel.ForgetViewModel
import com.nipun.riceselling.viewModel.ForgetViewModelFactory
import com.nipun.riceselling.viewModel.VerifyResetTokenViewModel
import com.nipun.riceselling.viewModel.VerifyResetTokenViewModelFactory
import kotlinx.android.synthetic.main.activity_forget_verify.*
import kotlinx.android.synthetic.main.include_toolbar.view.*

class ForgetVerifyActivity : BaseActivity() {
    var email: String? = null
    private val verifyTokenViewModel: VerifyResetTokenViewModel by lazy {
        val viewModelProviderFactory =
            VerifyResetTokenViewModelFactory(
                this
            )
        ViewModelProvider(
            this,
            viewModelProviderFactory
        )[VerifyResetTokenViewModel::class.java]
    }
    private val forgetViewModel: ForgetViewModel by lazy {
        val viewModelProviderFactory =
            ForgetViewModelFactory(
                this
            )
        ViewModelProvider(
            this,
            viewModelProviderFactory
        )[ForgetViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_verify)
        email = intent.getStringExtra("email")
        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        include.backButton.setOnClickListener {
            finish()
        }
        include.tvToolbar.text = "Verify Email"
        tvEmail.text = email
        verifyButton.setOnClickListener {
            callVerifyApi(firstPinView.text.toString())
        }
        tvResend.setOnClickListener {
            forgetApi(email!!)
        }
    }

    private fun callVerifyApi(editTextValue: String) {
        verifyTokenViewModel.verifyTokenApi(email!!,editTextValue,this).observe(this,{
            val intent = Intent(this, NewPasswordActivity::class.java)
            intent.putExtra("token",editTextValue)
            intent.putExtra("email",email)
            startActivity(intent)
            finish()
        })
    }

    private fun forgetApi(editText: String) {
        forgetViewModel.forgetApi(editText,this).observe(this,{
            MotionToast.createToast(
                this,
                "Success",
                "OTP Resend Successfully!",
                MotionToastStyle.SUCCESS,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(this, R.font.helvetica_regular)
            )
        })
    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, ForgetPasswordActivity::class.java))
        finish()
    }
}