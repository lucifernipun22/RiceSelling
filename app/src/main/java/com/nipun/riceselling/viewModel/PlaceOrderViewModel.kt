package com.nipun.riceselling.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nipun.riceselling.model.MyCart
import com.nipun.riceselling.model.PlaceOrderModel
import com.nipun.riceselling.repository.PlaceOrderRepository
import kotlinx.coroutines.launch

class PlaceOrderViewModel(var placeOrderRepository: PlaceOrderRepository) : ViewModel() {
    var loginResponseData = MutableLiveData<PlaceOrderModel>()

    fun placeOrderApi(
        context: Context,
        token: String,
        address: ArrayList<MyCart>,
        total: String?,
        type: String?,
        coupon: String?,
        discount: String?,
        addressPosition: Int,
        paymentMethod: String
    ): LiveData<PlaceOrderModel> {
        viewModelScope.launch {
            val uploadResponse = placeOrderRepository.hitPlaceOrder(context,token,
                address,total, type, coupon, discount,addressPosition,paymentMethod
            )
            loginResponseData = uploadResponse
        }
        return loginResponseData
    }
}