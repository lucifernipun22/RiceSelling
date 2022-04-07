package com.nipun.riceselling.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nipun.riceselling.repository.NotificationListRepository
import com.nipun.riceselling.utils.BaseActivity

class NotificationViewModelFactory(val baseActivity: BaseActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = NotificationListRepository(
            baseActivity = baseActivity
        )
        val viewModel = NotificationViewModel(repository)
        return viewModel as T
    }
}