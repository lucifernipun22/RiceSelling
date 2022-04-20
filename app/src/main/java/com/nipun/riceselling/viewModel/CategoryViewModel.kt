package com.nipun.riceselling.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nipun.riceselling.model.CategoryListModel
import com.nipun.riceselling.repository.CategoryRepository
import kotlinx.coroutines.launch

class CategoryViewModel(var categoryRepository: CategoryRepository) : ViewModel() {
    var loginResponseData = MutableLiveData<CategoryListModel>()

    fun categoryApi(context: Context): LiveData<CategoryListModel> {
        viewModelScope.launch {
            val uploadResponse = categoryRepository.hitCategoryApi(context)
            loginResponseData = uploadResponse
        }
        return loginResponseData
    }
}