package com.nipun.riceselling.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nipun.riceselling.repository.SaveNewPassRepository
import com.nipun.riceselling.repository.VerifyResetTokenRepository
import com.nipun.riceselling.utils.BaseActivity

class SavePassViewModelFactory(val baseActivity: BaseActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = SaveNewPassRepository(
            baseActivity = baseActivity
        )
        val viewModel = SavePassViewModel(repository)
        return viewModel as T
    }
}