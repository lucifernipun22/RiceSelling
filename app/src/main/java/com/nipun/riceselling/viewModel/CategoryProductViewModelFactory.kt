package com.nipun.riceselling.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nipun.riceselling.repository.CategoryProductRepository
import com.nipun.riceselling.utils.BaseActivity

class CategoryProductViewModelFactory(val baseActivity: BaseActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = CategoryProductRepository(
            baseActivity = baseActivity
        )
        val viewModel = CategoryProductViewModel(repository)
        return viewModel as T
    }
}