package com.nipun.riceselling.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nipun.riceselling.model.PopularProductsModel
import com.nipun.riceselling.repository.PopularProductRepository
import kotlinx.coroutines.launch

class PopularProductViewModel(var popularProductRepository: PopularProductRepository) :
    ViewModel() {
    var loginResponseData = MutableLiveData<PopularProductsModel>()

    fun bannerApi(context: Context): LiveData<PopularProductsModel> {
        viewModelScope.launch {
            val uploadResponse = popularProductRepository.hitPopularProductApi(context)
            loginResponseData = uploadResponse
        }
        return loginResponseData
    }
}