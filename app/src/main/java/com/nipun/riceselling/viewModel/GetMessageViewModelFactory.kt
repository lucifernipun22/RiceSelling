package com.nipun.riceselling.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nipun.riceselling.repository.GetMessageRepository
import com.nipun.riceselling.utils.BaseActivity

class GetMessageViewModelFactory(val baseActivity: BaseActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = GetMessageRepository(
            baseActivity = baseActivity
        )
        val viewModel = GetMessageViewModel(repository)
        return viewModel as T
    }
}