package com.nipun.riceselling.retrofit

import okhttp3.Headers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface ApiResponse {
    fun onProgressStart(startProgress: Boolean)
    fun onApiSuccess(
        responseCode: Int,
        responseMessage: String,
        response: String?,
        responseHeaders: Headers?
    )

    fun <T> onAPiFailure(
        errorCode: Int,
        t: Throwable?,
        response: Response<T>?,
        call: Call<T>?,
        callBack: Callback<T>?
    )

    fun onProgressStop()
}