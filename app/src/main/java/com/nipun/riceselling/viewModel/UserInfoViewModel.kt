package com.nipun.riceselling.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nipun.riceselling.model.UserInfoModel
import com.nipun.riceselling.repository.UserInfoRepository
import kotlinx.coroutines.launch

class UserInfoViewModel(var userInfoRepository: UserInfoRepository): ViewModel() {
    var loginResponseData = MutableLiveData<UserInfoModel>()

    fun couponListApi(token: String, context: Context): LiveData<UserInfoModel> {
        viewModelScope.launch {
            val uploadResponse = userInfoRepository.hitUserInfoApi(token,context)
            loginResponseData = uploadResponse
        }
        return loginResponseData
    }
}