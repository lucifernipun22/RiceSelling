package com.nipun.riceselling.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.nipun.riceselling.BaseFragment
import com.nipun.riceselling.R
import com.nipun.riceselling.SessionManager
import com.nipun.riceselling.activity.*
import com.nipun.riceselling.viewModel.UserInfoViewModel
import com.nipun.riceselling.viewModel.UserInfoViewModelFactory
import kotlinx.android.synthetic.main.fragment_setting.*
import kotlinx.android.synthetic.main.include_offer_toolbar.view.*
import kotlinx.android.synthetic.main.item_setting_list.*


class SettingFragment : BaseFragment(), View.OnClickListener {
    private var sessionManager: SessionManager? = null

    private val userInfoViewModel: UserInfoViewModel by lazy {
        val viewModelProviderFactory =
            UserInfoViewModelFactory(
                baseActivity!!
            )
        ViewModelProvider(
            this,
            viewModelProviderFactory
        )[UserInfoViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManager = SessionManager(baseActivity)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        toolbar.textView6.text = "Settings"
        toolbar.imageView4.visibility = View.GONE
        userInfoViewModel.couponListApi(
            sessionManager!!.getStringData("token")!!,
            baseActivity!!.baseContext
        ).observe(this, {
            tv_name.setText(it.f_name + " " + it.l_name)
        })
        setOnClickListner()

    }

    private fun setOnClickListner() {
        ll_myProfile.setOnClickListener(this)
        ll_terms.setOnClickListener(this)
        ll_privacy.setOnClickListener(this)
        ll_about_us.setOnClickListener(this)
        ll_live_chat.setOnClickListener(this)
        ll_logout.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.ll_myProfile->{
                val intent = Intent(baseActivity?.baseContext, MyProfileActivity::class.java)
                startActivity(intent)
            }
            R.id.ll_terms->{
                val intent = Intent(baseActivity?.baseContext, TermsConditionActivity::class.java)
                startActivity(intent)
            }
            R.id.ll_privacy->{
                val intent = Intent(baseActivity?.baseContext, PrivacyActivity::class.java)
                startActivity(intent)
            }
            R.id.ll_about_us->{
                val intent = Intent(baseActivity?.baseContext, AboutUsActivity::class.java)
                startActivity(intent)
            }
            R.id.ll_live_chat->{
                val intent = Intent(baseActivity?.baseContext,LiveChatActivity::class.java)
                startActivity(intent)
            }
            R.id.ll_logout->{
                if (sessionManager!!.getBooleanData(SessionManager.login)) {
                    sessionManager!!.logoutUser()
                    startActivity(Intent(baseActivity?.baseContext, LoginActivity::class.java))
                } else {
                    startActivity(Intent(baseActivity?.baseContext, LoginActivity::class.java))
                }
            }
        }
    }

}