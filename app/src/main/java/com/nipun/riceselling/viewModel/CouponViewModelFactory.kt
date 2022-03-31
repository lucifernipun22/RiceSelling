package com.nipun.riceselling.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nipun.riceselling.repository.CouponRepository
import com.nipun.riceselling.repository.ForgetPasswordRepository
import com.nipun.riceselling.utils.BaseActivity

class CouponViewModelFactory(val baseActivity: BaseActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = CouponRepository(
            baseActivity = baseActivity
        )
        val viewModel = CouponViewModel(repository)
        return viewModel as T
    }
}