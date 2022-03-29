package com.nipun.riceselling.utils

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.nipun.riceselling.SessionManager
import com.nipun.riceselling.retrofit.ApiResponse
import com.nipun.riceselling.retrofit.ApiService
import com.nipun.riceselling.retrofit.RetrofitClient
import okhttp3.Headers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit




open class BaseActivity: AppCompatActivity(), ApiResponse {

    var retrofitClient: RetrofitClient? = null
    var retrofit: Retrofit? = null
    var apiService: ApiService? = null
    var custPrograssbar: CustomProgressbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        custPrograssbar = CustomProgressbar()
        retrofitClient = RetrofitClient.getInstance(this)
        retrofit = retrofitClient!!.getClient(Constants.API_BASE_URL)
        apiService = retrofit!!.create(ApiService::class.java)
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
        custPrograssbar!!.progressCreate(this)
    }

    fun noInternetAlertBox(
        msg: String
    ) {
        AlertDialog.Builder(this)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle("Internet Connection Alert")
            .setMessage("Please Check Your Internet Connection")
            .setPositiveButton(
                "Close"
            ) { dialogInterface, i -> finish() }.show()
    }

}