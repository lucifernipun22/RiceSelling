package com.nipun.riceselling.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nipun.riceselling.model.AddressModel
import com.nipun.riceselling.repository.AddressFetchRepository
import kotlinx.coroutines.launch

class AddressFetchViewModel(var addressFetchRepository: AddressFetchRepository) : ViewModel() {
    var loginResponseData = MutableLiveData<AddressModel>()

    fun addressFetchApi( context: Context, token: String): LiveData<AddressModel> {
        viewModelScope.launch {
            val uploadResponse = addressFetchRepository.hitAddressFetch(context, token)
            loginResponseData = uploadResponse
        }
        return loginResponseData
    }
}