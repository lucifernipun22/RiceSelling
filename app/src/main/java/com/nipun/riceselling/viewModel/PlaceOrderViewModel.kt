package com.nipun.riceselling.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nipun.riceselling.model.ForgetPasswordModel
import com.nipun.riceselling.model.PlaceOrderModel
import com.nipun.riceselling.repository.AddAddressRepository
import com.nipun.riceselling.repository.PlaceOrderRepository
import kotlinx.coroutines.launch

class PlaceOrderViewModel(var placeOrderRepository: PlaceOrderRepository) : ViewModel() {
    var loginResponseData = MutableLiveData<PlaceOrderModel>()

    fun placeOrderApi(
        address: String,
        address_type: String,
        contact_person_name: String,
        contact_person_number: String,
        created_at: String,
        id: Int,
        latitude: String,
        longitude: String,
        updated_at: String,
        user_id: Int, context: Context, token: String
    ): LiveData<PlaceOrderModel> {
        viewModelScope.launch {
            val uploadResponse = placeOrderRepository.hitPlaceOrder(
                address,
                address_type,
                contact_person_name,
                contact_person_number,
                created_at,
                id,
                latitude,
                longitude,
                updated_at,
                user_id,
                context,
                token
            )
            loginResponseData = uploadResponse
        }
        return loginResponseData
    }
}