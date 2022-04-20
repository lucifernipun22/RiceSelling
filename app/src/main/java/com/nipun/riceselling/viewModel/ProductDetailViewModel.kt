package com.nipun.riceselling.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nipun.riceselling.model.ProductDetailModel
import com.nipun.riceselling.repository.ProductDetailRepository
import kotlinx.coroutines.launch

class ProductDetailViewModel(var productDetailRepository: ProductDetailRepository) :
    ViewModel() {
    var loginResponseData = MutableLiveData<ProductDetailModel>()

    fun productApi(id: String, context: Context): LiveData<ProductDetailModel> {
        viewModelScope.launch {
            val uploadResponse = productDetailRepository.hitProductDetailApi(id, context)
            loginResponseData = uploadResponse
        }
        return loginResponseData
    }
}