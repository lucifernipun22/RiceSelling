package com.nipun.riceselling.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nipun.riceselling.repository.AddAddressRepository
import com.nipun.riceselling.utils.BaseActivity

class AddAddressViewModelFactory (val baseActivity: BaseActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = AddAddressRepository(
            baseActivity = baseActivity
        )
        val viewModel = AddAddressViewModel(repository)
        return viewModel as T
    }
}