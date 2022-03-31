package com.nipun.riceselling.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nipun.riceselling.model.ForgetPasswordModel
import com.nipun.riceselling.repository.ForgetPasswordRepository
import kotlinx.coroutines.launch

class ForgetViewModel(var forgetPasswordRepository: ForgetPasswordRepository): ViewModel() {
    var loginResponseData = MutableLiveData<ForgetPasswordModel>()

    fun forgetApi(editEmail: String, context: Context): LiveData<ForgetPasswordModel> {
        viewModelScope.launch {
            val uploadResponse = forgetPasswordRepository.hitForgetApi(editEmail,context)
            loginResponseData = uploadResponse
        }
        return loginResponseData
    }
}