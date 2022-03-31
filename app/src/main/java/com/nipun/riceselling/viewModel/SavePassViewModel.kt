package com.nipun.riceselling.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nipun.riceselling.model.ForgetPasswordModel
import com.nipun.riceselling.repository.SaveNewPassRepository
import com.nipun.riceselling.repository.VerifyResetTokenRepository
import kotlinx.coroutines.launch

class SavePassViewModel(var saveNewPassRepository: SaveNewPassRepository) : ViewModel() {
    var savePassResponseData = MutableLiveData<ForgetPasswordModel>()

    fun savePassApi(
        editEmail: String,
        token: String,
        newTv: String,
        confirmTv: String,
        context: Context
    ): LiveData<ForgetPasswordModel> {
        viewModelScope.launch {
            val uploadResponse =
                saveNewPassRepository.hitSavePassApi(editEmail, token, newTv, confirmTv, context)
            savePassResponseData = uploadResponse
        }
        return savePassResponseData
    }
}