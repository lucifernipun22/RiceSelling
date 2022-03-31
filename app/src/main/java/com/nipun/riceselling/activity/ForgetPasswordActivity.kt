package com.nipun.riceselling.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import com.nipun.riceselling.R
import com.nipun.riceselling.utils.BaseActivity
import com.nipun.riceselling.utils.motionToast.MotionToast
import com.nipun.riceselling.utils.motionToast.MotionToastStyle
import com.nipun.riceselling.viewModel.ForgetViewModel
import com.nipun.riceselling.viewModel.ForgetViewModelFactory
import kotlinx.android.synthetic.main.activity_forget_password.*
import kotlinx.android.synthetic.main.activity_forget_password.editTextEmail
import kotlinx.android.synthetic.main.include_toolbar.view.*

class ForgetPasswordActivity : BaseActivity() {
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
        setContentView(R.layout.activity_forget_password)
        initView()
    }

    private fun initView() {
        include.backButton.setOnClickListener {
            finish()
        }
        editTextEmail.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) editTextEmail.hint = "" else editTextEmail.hint = "Email"
        }
        sendButton.setOnClickListener {
            if(editTextEmail.text.equals("")){
                MotionToast.createToast(this,
                    "Error ☹",
                    "Please Enter Correct Details!",
                    MotionToastStyle.ERROR,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(this, R.font.helvetica_regular))
            }else{
                forgetApi(editTextEmail.text.toString())
            }
        }


    }

    private fun forgetApi(editText: String) {
        forgetViewModel.forgetApi(editText,this).observe(this,{
            val intent = Intent(this,ForgetVerifyActivity::class.java)
            intent.putExtra("email",editText)
            startActivity(intent)
        })
    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}