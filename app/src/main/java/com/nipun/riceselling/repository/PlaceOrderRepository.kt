package com.nipun.riceselling.repository

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import com.nipun.riceselling.R
import com.nipun.riceselling.model.ForgetPasswordModel
import com.nipun.riceselling.model.PlaceOrderModel
import com.nipun.riceselling.utils.BaseActivity
import com.nipun.riceselling.utils.Constants
import com.nipun.riceselling.utils.Utiles
import com.nipun.riceselling.utils.motionToast.MotionToast
import com.nipun.riceselling.utils.motionToast.MotionToastStyle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaceOrderRepository(var baseActivity: BaseActivity) {

    private var couponRepository = MutableLiveData<PlaceOrderModel>()

    fun hitPlaceOrder(
        address: String,
        address_type: String,
        contact_person_name: String,
        contact_person_number: String,
        created_at: String,
        id: Int,
        latitude: String,
        longitude: String,
        updated_at: String,
        user_id: Int,
        context: Context,
        token: String
    ): MutableLiveData<PlaceOrderModel> {
        if (Utiles.Utiles.internetChack()) {
            val jsonObject = JsonObject()
            jsonObject.addProperty("id",id)
            jsonObject.addProperty("latitude", latitude)
            jsonObject.addProperty("longitude", longitude)
            jsonObject.addProperty("updated_at", updated_at)
            jsonObject.addProperty("user_id", user_id)
            jsonObject.addProperty("created_at", created_at)
            jsonObject.addProperty("contact_person_number", contact_person_number)
            jsonObject.addProperty("contact_person_name", contact_person_name)
            jsonObject.addProperty("address_type", address_type)
            jsonObject.addProperty("address",address)

            val call: Call<PlaceOrderModel> =
                baseActivity.apiService!!.placeOrder("Bearer ${token}",jsonObject)
            baseActivity.startProgress()
            call.enqueue(object : Callback<PlaceOrderModel?> {
                @SuppressLint("NullSafeMutableLiveData")
                override fun onResponse(
                    call: Call<PlaceOrderModel?>,
                    response: Response<PlaceOrderModel?>
                ) {
                    baseActivity.stopProgress()
                    if (response.isSuccessful) {
                        if (response.code() == Constants.STATUS_OK) {
                            if (response.body() != null) {
                                val body: PlaceOrderModel? = response.body()
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

                override fun onFailure(call: Call<PlaceOrderModel?>, t: Throwable) {
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