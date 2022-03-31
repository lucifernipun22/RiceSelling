package com.nipun.riceselling.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nipun.riceselling.model.LoginModel
import com.nipun.riceselling.repository.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(var loginRepository: LoginRepository): ViewModel() {
    var loginResponseData = MutableLiveData<LoginModel>()

    fun loginApi(editEmail: String, editPass: String, context: Context): LiveData<LoginModel> {
        viewModelScope.launch {
            val uploadResponse = loginRepository.hitLoginApi(editEmail,editPass,context)
            loginResponseData = uploadResponse
        }
        return loginResponseData
    }
}