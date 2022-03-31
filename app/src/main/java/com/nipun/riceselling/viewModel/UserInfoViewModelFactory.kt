package com.nipun.riceselling.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nipun.riceselling.repository.UserInfoRepository
import com.nipun.riceselling.utils.BaseActivity

class UserInfoViewModelFactory(val baseActivity: BaseActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = UserInfoRepository(
            baseActivity = baseActivity
        )
        val viewModel = UserInfoViewModel(repository)
        return viewModel as T
    }
}