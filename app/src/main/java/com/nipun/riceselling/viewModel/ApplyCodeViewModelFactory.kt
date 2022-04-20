package com.nipun.riceselling.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nipun.riceselling.repository.ApplyCodeRepository
import com.nipun.riceselling.utils.BaseActivity

class ApplyCodeViewModelFactory(val baseActivity: BaseActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = ApplyCodeRepository(
            baseActivity = baseActivity
        )
        val viewModel = ApplyCodeViewModel(repository)
        return viewModel as T
    }
}