package com.nipun.riceselling.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nipun.riceselling.repository.SendMessageRepository
import com.nipun.riceselling.utils.BaseActivity

class SendMessageViewModelFactory(val baseActivity: BaseActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = SendMessageRepository(
            baseActivity = baseActivity
        )
        val viewModel = SendMessageViewModel(repository)
        return viewModel as T
    }
}