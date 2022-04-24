package com.nipun.riceselling.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nipun.riceselling.repository.AddressFetchRepository
import com.nipun.riceselling.utils.BaseActivity

class AddressFetchViewModelFactory(val baseActivity: BaseActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = AddressFetchRepository(
            baseActivity = baseActivity
        )
        val viewModel = AddressFetchViewModel(repository)
        return viewModel as T
    }
}