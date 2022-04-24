package com.nipun.riceselling.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nipun.riceselling.model.AddressModelItem
import com.nipun.riceselling.model.ForgetPasswordModel
import com.nipun.riceselling.repository.AddAddressRepository
import kotlinx.coroutines.launch

class AddAddressViewModel(var addressFetchRepository: AddAddressRepository) : ViewModel() {
    var loginResponseData = MutableLiveData<ForgetPasswordModel>()

    fun addAddressApi(address: String,
                       address_type: String,
                       contact_person_name: String,
                       contact_person_number: String,
                       created_at: String,
                       id: Int,
                       latitude: String,
                       longitude: String,
                       updated_at: String,
                       user_id: Int,context: Context, token: String): LiveData<ForgetPasswordModel> {
        viewModelScope.launch {
            val uploadResponse = addressFetchRepository.hitAddAddress(address, address_type, contact_person_name, contact_person_number, created_at, id, latitude, longitude, updated_at, user_id, context, token)
            loginResponseData = uploadResponse
        }
        return loginResponseData
    }
}