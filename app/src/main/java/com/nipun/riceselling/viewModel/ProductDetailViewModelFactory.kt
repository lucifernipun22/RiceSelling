package com.nipun.riceselling.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nipun.riceselling.repository.ProductDetailRepository
import com.nipun.riceselling.utils.BaseActivity

class ProductDetailViewModelFactory(val baseActivity: BaseActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = ProductDetailRepository(
            baseActivity = baseActivity
        )
        val viewModel = ProductDetailViewModel(repository)
        return viewModel as T
    }
}