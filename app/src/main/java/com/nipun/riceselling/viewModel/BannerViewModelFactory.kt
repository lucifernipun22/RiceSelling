package com.nipun.riceselling.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nipun.riceselling.repository.BannerRepository
import com.nipun.riceselling.utils.BaseActivity

class BannerViewModelFactory(val baseActivity: BaseActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = BannerRepository(
            baseActivity = baseActivity
        )
        val viewModel = BannerViewModel(repository)
        return viewModel as T
    }
}