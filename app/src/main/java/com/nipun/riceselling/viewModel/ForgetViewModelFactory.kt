package com.nipun.riceselling.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nipun.riceselling.repository.ForgetPasswordRepository
import com.nipun.riceselling.repository.LoginRepository
import com.nipun.riceselling.utils.BaseActivity

class ForgetViewModelFactory(val baseActivity: BaseActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = ForgetPasswordRepository(
            baseActivity = baseActivity
        )
        val viewModel = ForgetViewModel(repository)
        return viewModel as T
    }
}