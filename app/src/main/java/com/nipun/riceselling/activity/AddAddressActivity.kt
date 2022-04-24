package com.nipun.riceselling.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nipun.riceselling.R
import com.nipun.riceselling.SessionManager
import com.nipun.riceselling.adapter.SlideAdapter
import com.nipun.riceselling.model.AddressModelItem
import com.nipun.riceselling.utils.BaseActivity
import com.nipun.riceselling.utils.motionToast.MotionToast
import com.nipun.riceselling.utils.motionToast.MotionToastStyle
import com.nipun.riceselling.viewModel.*
import kotlinx.android.synthetic.main.activity_add_address.*
import kotlinx.android.synthetic.main.activity_add_address.btn_checkout
import kotlinx.android.synthetic.main.activity_add_address.toolbar
import kotlinx.android.synthetic.main.include_offer_toolbar.view.*

class AddAddressActivity : BaseActivity() {

    internal var list = listOf<String>(
        "Home",
        "Workplace",
        "Other"
    )
    var address_type = ""
    var id = 0
    val names = listOf("John", "Doe", "David", "Jim", "Dan")
    val state = listOf("Hyderabad")
    private var sessionManager : SessionManager? = null
    private val addAddressViewModel: AddAddressViewModel by lazy {
        val viewModelProviderFactory =
            AddAddressViewModelFactory(
                this
            )
        ViewModelProvider(
            this,
            viewModelProviderFactory
        )[AddAddressViewModel::class.java]
    }

    private val userInfoViewModel: UserInfoViewModel by lazy {
        val viewModelProviderFactory =
            UserInfoViewModelFactory(
                this
            )
        ViewModelProvider(
            this,
            viewModelProviderFactory
        )[UserInfoViewModel::class.java]
    }
    var adapter: SlideAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_address)
        toolbar.rootView.textView6.text = "Add New Address"
        toolbar.rootView.imageView4.setOnClickListener {
            finish()
        }
        sessionManager = SessionManager(this)
        userInfoViewModel.couponListApi(sessionManager!!.getStringData("token").toString(),this).observe(this,{
            id = it.id!!
        })
        recyclerViewAddType.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapter = SlideAdapter(list, this)
        recyclerViewAddType.adapter = adapter
        editTextEmail.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) editTextEmail.hint = "" else editTextEmail.hint = "Customer Name"
        }
        editName.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) editName.hint = "" else editName.hint = "House No."
        }
        number.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) number.hint = "" else number.hint = "Number"
        }

        val atvNamesAdapter = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, names
        )

        atv_names.setAdapter(atvNamesAdapter)

        atv_names.setOnFocusChangeListener { _, _ ->
            atv_names.showDropDown()
        }


        val atvStateAdapter = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, state
        )

        atv_state.setAdapter(atvStateAdapter)

        atv_state.setOnFocusChangeListener { _, _ ->
            atv_state.showDropDown()
        }
        btn_checkout.setOnClickListener {
            if (validation()) {

                addAddressViewModel.addAddressApi("${editName.text.toString()} ${atv_names.text.toString()} ${atv_state.text.toString()}",address_type,
                    editTextEmail.text.toString(),
                    number.text.toString(),
                    "",
                    id,
                    "",
                    "",
                    "",
                    id,this,sessionManager!!.getStringData("token").toString()).observe(this,{
                    val intent = Intent(this,CheckOutActivity::class.java)
                    startActivity(intent)
                    finish()
                })
            }
        }


    }

    private fun validation(): Boolean {
        if (editTextEmail.text.toString().isEmpty()) {
            MotionToast.createToast(
                this,
                "Error",
                "Please Enter the Customer Name",
                MotionToastStyle.ERROR,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(this, R.font.helvetica_regular)
            )
            return false
        }
        if (editName.text.toString().isEmpty()) {
            MotionToast.createToast(
                this,
                "Error",
                "Please Enter House Number",
                MotionToastStyle.ERROR,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(this, R.font.helvetica_regular)
            )
            return false
        }
        if (atv_names.text.toString().isEmpty()) {
            MotionToast.createToast(
                this,
                "Error",
                "Please Enter Valid Pincode",
                MotionToastStyle.ERROR,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(this, R.font.helvetica_regular)
            )
            return false
        }
        if (atv_state.text.toString().isEmpty()&& !state.contains(atv_state.text.toString())) {
            MotionToast.createToast(
                this,
                "Error",
                "Please Enter Valid State",
                MotionToastStyle.ERROR,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(this, R.font.helvetica_regular)
            )
            return false
        }

        if (number.text.toString().isEmpty()) {
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

        if (!state.contains(atv_state.text.toString())) {
            MotionToast.createToast(
                this,
                "Error",
                "We are Currently Available in Hyderabad ",
                MotionToastStyle.ERROR,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(this, R.font.helvetica_regular)
            )
            return false
        }

        if (!names.contains(atv_names.text.toString())) {
            MotionToast.createToast(
                this,
                "Error",
                "Please Enter Valid Pincode",
                MotionToastStyle.ERROR,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(this, R.font.helvetica_regular)
            )
            return false
        }
        return true
    }
}