package com.nipun.riceselling.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import com.nipun.riceselling.R
import com.nipun.riceselling.SessionManager
import com.nipun.riceselling.utils.BaseActivity
import com.nipun.riceselling.utils.motionToast.MotionToast
import com.nipun.riceselling.utils.motionToast.MotionToastStyle
import com.nipun.riceselling.viewModel.SavePassViewModel
import com.nipun.riceselling.viewModel.SavePassViewModelFactory
import kotlinx.android.synthetic.main.activity_forget_verify.include
import kotlinx.android.synthetic.main.activity_new_password.*
import kotlinx.android.synthetic.main.include_toolbar.view.*

class NewPasswordActivity : BaseActivity() {
    private var token: String? = null
    var email: String? = null
    var sessionManager: SessionManager? = null
    private val savePassViewModel: SavePassViewModel by lazy {
        val viewModelProviderFactory =
            SavePassViewModelFactory(
                this
            )
        ViewModelProvider(
            this,
            viewModelProviderFactory
        )[SavePassViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_password)
        sessionManager = SessionManager(this)
        token = intent.getStringExtra("token")
        email = intent.getStringExtra("email")
        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        include.backButton.setOnClickListener {
            finish()
        }
        include.tvToolbar.text = "Create new password"

        newTv.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) newTv.hint = "" else newTv.hint = "New Password"
        }
        confirmTv.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) confirmTv.hint = "" else confirmTv.hint = "Confirm Password"
        }
        saveButton.setOnClickListener {
            if (validation()) {
                callSavePassApi(newTv.text.toString(), confirmTv.text.toString())
            }
        }
    }

    private fun validation(): Boolean {
        if (newTv.text.toString().isEmpty()) {
            MotionToast.createToast(
                this,
                "Error",
                "Enter Password",
                MotionToastStyle.ERROR,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(this, R.font.helvetica_regular)
            )
            return false
        }
        if (confirmTv.text.toString().isEmpty()) {
            MotionToast.createToast(
                this,
                "Error",
                "Enter Confirm Password",
                MotionToastStyle.ERROR,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(this, R.font.helvetica_regular)
            )
            return false
        }
        if (confirmTv.text.toString() != newTv.text.toString()) {
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
        if (newTv.text.length<=6) {
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
        return true
    }

    private fun callSavePassApi(newTv: String, confirmTv: String) {
        savePassViewModel.savePassApi(email!!, token!!, newTv, confirmTv, this).observe(this, {
            sessionManager?.setBooleanData(SessionManager.login, true)
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        })
    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, ForgetPasswordActivity::class.java))
        finish()
    }
}