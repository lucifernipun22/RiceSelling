package com.nipun.riceselling.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.nipun.riceselling.R
import com.nipun.riceselling.utils.BaseActivity
import com.nipun.riceselling.utils.motionToast.MotionToast
import com.nipun.riceselling.utils.motionToast.MotionToastStyle
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.loginButton
import kotlinx.android.synthetic.main.activity_sign_up.tvSignup

class SignUpActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        initView()
    }

    private fun initView() {
        editNumber.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) editNumber.hint = "" else editNumber.hint = "Mobile Number"
        }
        loginButton.setOnClickListener {
            if(validation()) {
                val intent = Intent(this, RegisterActivity::class.java)
                intent.putExtra("number", editNumber.text.toString())
                startActivity(intent)
            }
        }
        tvSignup.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validation(): Boolean {
        if (editNumber.text.toString().isEmpty()) {
            MotionToast.createToast(
                this,
                "Error",
                "Please Enter Valid Number",
                MotionToastStyle.ERROR,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(this, R.font.helvetica_regular)
            )
            return false
        }
        if (editNumber.text.length < 10) {
            MotionToast.createToast(
                this,
                "Error",
                "Please Enter Valid Number",
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
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}