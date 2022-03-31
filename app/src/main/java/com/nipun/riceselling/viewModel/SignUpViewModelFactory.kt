package com.nipun.riceselling.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nipun.riceselling.repository.SignUpRepository
import com.nipun.riceselling.repository.VerifyResetTokenRepository
import com.nipun.riceselling.utils.BaseActivity

class SignUpViewModelFactory(val baseActivity: BaseActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = SignUpRepository(
            baseActivity = baseActivity
        )
        val viewModel = SignUpViewModel(repository)
        return viewModel as T
    }
}