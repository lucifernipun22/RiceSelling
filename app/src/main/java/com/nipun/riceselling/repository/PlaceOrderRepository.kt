package com.nipun.riceselling.repository

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.nipun.riceselling.R
import com.nipun.riceselling.model.MyCart
import com.nipun.riceselling.model.PlaceOrderModel
import com.nipun.riceselling.utils.BaseActivity
import com.nipun.riceselling.utils.Constants
import com.nipun.riceselling.utils.Utiles
import com.nipun.riceselling.utils.motionToast.MotionToast
import com.nipun.riceselling.utils.motionToast.MotionToastStyle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class PlaceOrderRepository(var baseActivity: BaseActivity) {

    private var couponRepository = MutableLiveData<PlaceOrderModel>()

    fun hitPlaceOrder(
        context: Context,
        token: String,
        myCart: ArrayList<MyCart>,
        total: String?,
        type: String?,
        coupon: String?,
        discount: String?,
        addressPosition: Int,
        paymentMethod: String
    ): MutableLiveData<PlaceOrderModel> {
        if (Utiles.Utiles.internetChack()) {
            val calendar: Calendar = Calendar.getInstance()

            calendar.add(Calendar.DAY_OF_YEAR, 2)
            val Daytomorrow: Date = calendar.time
            val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")

            val tomorrowAsString: String = dateFormat.format(Daytomorrow)
            var jsonArray = JsonArray()
            var jsonArray2 = JsonArray()
            val jsonObject = JsonObject()
            for(i in myCart.indices){
                val jsonObjectData = JsonObject()
                jsonObjectData.addProperty("type", type)
                jsonArray2.add(jsonObjectData)
            }
            for (i in myCart.indices) {
                val jsonObjectData = JsonObject()
                val myCart: MyCart = myCart[i]
                jsonObjectData.addProperty("product_id", myCart.pid)
                jsonObjectData.addProperty("price",total )
                jsonObjectData.addProperty("variant", "")
                jsonObjectData.add("variation", jsonArray2)
                jsonObjectData.addProperty("discount_amount", discount)
                jsonObjectData.addProperty("quantity", myCart.qty)
                jsonObjectData.add("tax_amount", jsonArray2)
                jsonArray.add(jsonObjectData)
            }

            jsonObject.add("cart",jsonArray)
            jsonObject.addProperty("coupon_discount_amount", discount?.toDouble())
            jsonObject.addProperty("coupon_discount_title", coupon)
            jsonObject.addProperty("order_amount", total?.toDouble()!! - discount!!.toDouble())
            jsonObject.addProperty("order_type", type)
            jsonObject.addProperty("branch_id", 1)
            jsonObject.addProperty("delivery_address_id", addressPosition)
            jsonObject.addProperty("time_slot_id", 1)
            jsonObject.addProperty("delivery_date", tomorrowAsString)
            jsonObject.addProperty("payment_method",paymentMethod)
            jsonObject.addProperty("order_note", "")
            jsonObject.addProperty("coupon_code", coupon)
            jsonObject.addProperty("distance",0)
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