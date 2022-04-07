package com.nipun.riceselling.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nipun.riceselling.R
import com.nipun.riceselling.SessionManager
import com.nipun.riceselling.adapter.NotificationAdapter
import com.nipun.riceselling.utils.BaseActivity
import com.nipun.riceselling.viewModel.NotificationViewModel
import com.nipun.riceselling.viewModel.NotificationViewModelFactory
import kotlinx.android.synthetic.main.activity_live_chat.*
import kotlinx.android.synthetic.main.activity_myprofile.*
import kotlinx.android.synthetic.main.activity_myprofile.toolbar
import kotlinx.android.synthetic.main.activity_notification.*
import kotlinx.android.synthetic.main.include_offer_toolbar.view.*

class NotificationActivity : BaseActivity() {
    private var sessionManager: SessionManager? = null
    private var notificationAdapter: NotificationAdapter? = null
    private val notificationViewModel: NotificationViewModel by lazy {
        val viewModelProviderFactory =
            NotificationViewModelFactory(
                this
            )
        ViewModelProvider(
            this,
            viewModelProviderFactory
        )[NotificationViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        initView()
        callNotificationApi()
    }

    private fun callNotificationApi() {
        notificationViewModel.notificationApi(sessionManager?.getStringData("token")!!, this)
            .observe(this, {
                notificationAdapter = NotificationAdapter(it,this)
                recyclerViewNoti.adapter =  notificationAdapter
            })
    }

    private fun initView() {
        recyclerViewNoti.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewNoti.isNestedScrollingEnabled = false
        sessionManager = SessionManager(this)
        toolbar.textView6.text = "Notifications"
    }
}