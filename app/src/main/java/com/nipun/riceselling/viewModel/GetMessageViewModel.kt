package com.nipun.riceselling.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nipun.riceselling.model.GetMessagesModel
import com.nipun.riceselling.repository.GetMessageRepository
import kotlinx.coroutines.launch

class GetMessageViewModel(var getMessageRepository: GetMessageRepository) : ViewModel() {
    var loginResponseData = MutableLiveData<GetMessagesModel>()

    fun getChatApi(token: String, context: Context): LiveData<GetMessagesModel> {
        viewModelScope.launch {
            val uploadResponse = getMessageRepository.hitGetMessageApi(token, context)
            loginResponseData = uploadResponse
        }
        return loginResponseData
    }
}