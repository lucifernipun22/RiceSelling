package com.nipun.riceselling.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nipun.riceselling.model.BannerModel
import com.nipun.riceselling.repository.BannerRepository
import kotlinx.coroutines.launch

class BannerViewModel(var bannerRepository: BannerRepository): ViewModel() {
    var loginResponseData = MutableLiveData<BannerModel>()

    fun bannerApi( context: Context): LiveData<BannerModel> {
        viewModelScope.launch {
            val uploadResponse = bannerRepository.hitBannerApi(context)
            loginResponseData = uploadResponse
        }
        return loginResponseData
    }
}