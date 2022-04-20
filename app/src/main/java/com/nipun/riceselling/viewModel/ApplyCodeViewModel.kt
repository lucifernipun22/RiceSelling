package com.nipun.riceselling.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nipun.riceselling.model.ApplyCodeModel
import com.nipun.riceselling.repository.ApplyCodeRepository
import kotlinx.coroutines.launch

class ApplyCodeViewModel(var applyCodeRepository: ApplyCodeRepository) : ViewModel() {
    var loginResponseData = MutableLiveData<ApplyCodeModel>()

    fun bannerApi(code: String, context: Context,token: String): LiveData<ApplyCodeModel> {
        viewModelScope.launch {
            val uploadResponse = applyCodeRepository.hitApplyCodeApi(code, context,token)
            loginResponseData = uploadResponse
        }
        return loginResponseData
    }
}