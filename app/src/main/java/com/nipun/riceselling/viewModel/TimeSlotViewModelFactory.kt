package com.nipun.riceselling.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nipun.riceselling.repository.TimeSlotRepository
import com.nipun.riceselling.utils.BaseActivity

class TimeSlotViewModelFactory(val baseActivity: BaseActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = TimeSlotRepository(
            baseActivity = baseActivity
        )
        val viewModel = TimeSlotViewModel(repository)
        return viewModel as T
    }
}