package com.nipun.riceselling.repository

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.MutableLiveData
import com.nipun.riceselling.R
import com.nipun.riceselling.model.AddressModel
import com.nipun.riceselling.utils.BaseActivity
import com.nipun.riceselling.utils.Constants
import com.nipun.riceselling.utils.Utiles
import com.nipun.riceselling.utils.motionToast.MotionToast
import com.nipun.riceselling.utils.motionToast.MotionToastStyle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddressFetchRepository(var baseActivity: BaseActivity) {

    private var couponRepository = MutableLiveData<AddressModel>()

    fun hitAddressFetch(
        context: Context,
        token: String
    ): MutableLiveData<AddressModel> {
        if (Utiles.Utiles.internetChack()) {
            val call: Call<AddressModel> =
                baseActivity.apiService!!.addressFetch("Bearer ${token}")
            baseActivity.startProgress()
            call.enqueue(object : Callback<AddressModel?> {
                @SuppressLint("NullSafeMutableLiveData")
                override fun onResponse(
                    call: Call<AddressModel?>,
                    response: Response<AddressModel?>
                ) {
                    baseActivity.stopProgress()
                    if (response.isSuccessful) {
                        if (response.code() == Constants.STATUS_OK) {
                            if (response.body() != null) {
                                val body: AddressModel? = response.body()
                                couponRepository.value = body
                            }
                        } else {
                            MotionToast.createToast(
                                baseActivity,
                                "Failed",
                                "Try Again!",
                                MotionToastStyle.ERROR,
                                MotionToast.GRAVITY_BOTTOM,
                                MotionToast.LONG_DURATION,
                                ResourcesCompat.getFont(context, R.font.helvetica_regular)
                            )
                            baseActivity.onAPiFailure(response.code(), null, response, call, this)
                        }
                    } else {
                        MotionToast.createToast(
                            baseActivity,
                            "Failed",
                            "Try Again!",
                            MotionToastStyle.ERROR,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(context, R.font.helvetica_regular)
                        )
                        baseActivity.onAPiFailure(response.code(), null, response, call, this)
                    }
                }

                override fun onFailure(call: Call<AddressModel?>, t: Throwable) {
                    baseActivity.stopProgress()
                    MotionToast.createToast(
                        baseActivity,
                        "Failed",
                        "Try Again!",
                        MotionToastStyle.ERROR,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(context, R.font.helvetica_regular)
                    )
                    baseActivity.onAPiFailure(0, t, null, call, this)
                }
            })
        } else {
            MotionToast.createToast(
                baseActivity,
                "Error",
                "No Internet Connection!",
                MotionToastStyle.NO_INTERNET,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(context, R.font.helvetica_regular)
            )
        }
        return couponRepository
    }

}