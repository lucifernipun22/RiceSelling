package com.nipun.riceselling.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nipun.riceselling.model.CategoryProductModel
import com.nipun.riceselling.repository.CategoryProductRepository
import kotlinx.coroutines.launch

class CategoryProductViewModel(var categoryProductRepository: CategoryProductRepository) :
    ViewModel() {
    var loginResponseData = MutableLiveData<CategoryProductModel>()

    fun categoryProductApi(id: String, context: Context): LiveData<CategoryProductModel> {
        viewModelScope.launch {
            val uploadResponse = categoryProductRepository.hitCategoryProductApi(id, context)
            loginResponseData = uploadResponse
        }
        return loginResponseData
    }
}