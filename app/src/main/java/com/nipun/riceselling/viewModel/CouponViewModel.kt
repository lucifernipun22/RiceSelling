package com.nipun.riceselling.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nipun.riceselling.model.CouponModel
import com.nipun.riceselling.repository.CouponRepository
import kotlinx.coroutines.launch

class CouponViewModel(var couponRepository: CouponRepository): ViewModel() {
    var loginResponseData = MutableLiveData<CouponModel>()

    fun couponListApi(token: String, context: Context): LiveData<CouponModel> {
        viewModelScope.launch {
            val uploadResponse = couponRepository.hitCouponApi(token,context)
            loginResponseData = uploadResponse
        }
        return loginResponseData
    }
}