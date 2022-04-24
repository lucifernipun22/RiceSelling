package com.nipun.riceselling.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nipun.riceselling.repository.PlaceOrderRepository
import com.nipun.riceselling.utils.BaseActivity

class PlaceOrderViewModelFactory(val baseActivity: BaseActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = PlaceOrderRepository(
            baseActivity = baseActivity
        )
        val viewModel = PlaceOrderViewModel(repository)
        return viewModel as T
    }
}