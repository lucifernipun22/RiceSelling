package com.nipun.riceselling.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nipun.riceselling.repository.PopularProductRepository
import com.nipun.riceselling.utils.BaseActivity

class PopularProductViewModelFactory(val baseActivity: BaseActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = PopularProductRepository(
            baseActivity = baseActivity
        )
        val viewModel = PopularProductViewModel(repository)
        return viewModel as T
    }
}