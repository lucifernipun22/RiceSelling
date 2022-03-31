package com.nipun.riceselling.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nipun.riceselling.repository.VerifyResetTokenRepository
import com.nipun.riceselling.utils.BaseActivity

class VerifyResetTokenViewModelFactory(val baseActivity: BaseActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = VerifyResetTokenRepository(
            baseActivity = baseActivity
        )
        val viewModel = VerifyResetTokenViewModel(repository)
        return viewModel as T
    }
}