package com.nipun.riceselling.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nipun.riceselling.model.LoginModel
import com.nipun.riceselling.repository.SignUpRepository
import kotlinx.coroutines.launch

class SignUpViewModel(var signUpRepository: SignUpRepository) : ViewModel() {
    var savePassResponseData = MutableLiveData<LoginModel>()

    fun signUpApiApi(
        fName: String,
        lName: String,
        phone: String,
        email: String,
        password: String,
        context: Context
    ): LiveData<LoginModel> {
        viewModelScope.launch {
            val uploadResponse =
                signUpRepository.hitSignUpApi(fName, lName, phone, email, password, context)
            savePassResponseData = uploadResponse
        }
        return savePassResponseData
    }
}