package com.nipun.riceselling.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nipun.riceselling.repository.CategoryRepository
import com.nipun.riceselling.utils.BaseActivity

class CategoryViewModelFactory(val baseActivity: BaseActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = CategoryRepository(
            baseActivity = baseActivity
        )
        val viewModel = CategoryViewModel(repository)
        return viewModel as T
    }
}