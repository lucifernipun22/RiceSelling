package com.nipun.riceselling.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nipun.riceselling.model.TimeSlotModel
import com.nipun.riceselling.repository.TimeSlotRepository
import kotlinx.coroutines.launch

class TimeSlotViewModel(var timeSlotRepository: TimeSlotRepository) : ViewModel() {
    var loginResponseData = MutableLiveData<TimeSlotModel>()

    fun bannerApi(context: Context): LiveData<TimeSlotModel> {
        viewModelScope.launch {
            val uploadResponse = timeSlotRepository.hitTimeSlotApi(context)
            loginResponseData = uploadResponse
        }
        return loginResponseData
    }
}