package com.nipun.riceselling.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nipun.riceselling.repository.ConfigRepository
import com.nipun.riceselling.utils.BaseActivity

class ConfigViewModelFactory (val baseActivity: BaseActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = ConfigRepository(
            baseActivity = baseActivity
        )
        val viewModel = ConfigViewModel(repository)
        return viewModel as T
    }
}