package com.nipun.riceselling

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.nipun.riceselling.retrofit.ApiResponse
import com.nipun.riceselling.utils.BaseActivity
import com.nipun.riceselling.utils.CustomProgressbar
import okhttp3.Headers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseFragment: Fragment(), ApiResponse {
    var baseActivity: BaseActivity? = null
    var custPrograssbar: CustomProgressbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        custPrograssbar = CustomProgressbar()
        baseActivity = activity as BaseActivity?
    }
    override fun onProgressStart(startProgress: Boolean) {
        startProgress()
    }

    override fun onApiSuccess(
        responseCode: Int,
        responseMessage: String,
        response: String?,
        responseHeaders: Headers?
    ) {

    }

    override fun <T> onAPiFailure(
        errorCode: Int,
        t: Throwable?,
        response: Response<T>?,
        call: Call<T>?,
        callBack: Callback<T>?
    ) {

    }

    override fun onProgressStop() {
        stopProgress()
    }

    fun stopProgress() {
        custPrograssbar!!.closePrograssBar()

    }

    fun startProgress(){
        custPrograssbar!!.progressCreate(baseActivity)
    }
}