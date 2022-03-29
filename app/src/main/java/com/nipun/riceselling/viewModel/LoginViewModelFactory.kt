package com.nipun.riceselling.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nipun.riceselling.repository.LoginRepository
import com.nipun.riceselling.utils.BaseActivity

class LoginViewModelFactory(val baseActivity: BaseActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = LoginRepository(
            baseActivity = baseActivity
        )
        val viewModel = LoginViewModel(repository)
        return viewModel as T
    }
}