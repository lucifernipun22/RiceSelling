package com.nipun.riceselling.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nipun.riceselling.R
import com.nipun.riceselling.SessionManager
import com.nipun.riceselling.adapter.MessagesAdapter
import com.nipun.riceselling.utils.BaseActivity
import com.nipun.riceselling.viewModel.*
import kotlinx.android.synthetic.main.activity_live_chat.*
import kotlinx.android.synthetic.main.activity_myprofile.toolbar
import kotlinx.android.synthetic.main.include_offer_toolbar.view.*

class LiveChatActivity : BaseActivity() {
    private var sessionManager: SessionManager? = null
    private var getChatAdapter: MessagesAdapter? = null

    private val configViewModel: ConfigViewModel by lazy {
        val viewModelProviderFactory =
            ConfigViewModelFactory(
                this
            )
        ViewModelProvider(
            this,
            viewModelProviderFactory
        )[ConfigViewModel::class.java]
    }
    private val sendMessageViewModel: SendMessageViewModel by lazy {
        val viewModelProviderFactory =
            SendMessageViewModelFactory(
                this
            )
        ViewModelProvider(
            this,
            viewModelProviderFactory
        )[SendMessageViewModel::class.java]
    }

    private val getChatViewModel: GetMessageViewModel by lazy {
        val viewModelProviderFactory =
            GetMessageViewModelFactory(
                this
            )
        ViewModelProvider(
            this,
            viewModelProviderFactory
        )[GetMessageViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_chat)
        sessionManager = SessionManager(this)
        initView()
        getChatApi()
        configApiCall()
    }

    private fun getChatApi() {
        getChatViewModel.getChatApi(sessionManager?.getStringData("token")!!, this).observe(this, {
            getChatAdapter = MessagesAdapter(it[it.size - 1])
            recyclerChat.adapter = getChatAdapter

        })
    }

    private fun configApiCall() {
        configViewModel.configApi(this).observe(this, {

        })
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {

        sendButtonMessage.setOnClickListener {
            if(editTextMessage.text.length>0) {
                callSendMessageApi(editTextMessage.text.toString())
            }
        }

        recyclerChat.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        recyclerChat.isNestedScrollingEnabled = false
        toolbar.textView6.text = "Live Chat"
        toolbar.imageView4.setOnClickListener {
            finish()
        }
    }

    private fun callSendMessageApi(message: String) {
        sendMessageViewModel.sendMessageApi(this, sessionManager?.getStringData("token")!!, message)
            .observe(this, {
                editTextMessage.text.clear()
                getChatApi()
            })
    }

    override fun onResume() {
        super.onResume()


    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}