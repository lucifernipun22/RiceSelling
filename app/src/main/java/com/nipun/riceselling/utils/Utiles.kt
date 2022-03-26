package com.nipun.riceselling.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.provider.Settings
import android.util.Log
import com.nipun.riceselling.MyApplication
import java.io.InputStream
import java.io.OutputStream
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class Utiles {
    object Utiles {
        var isRef = false
        var isSelect = false
        var seletAddress = 0
        var isvarification = -1
        var isrates = false
        val date: String
            get() {
                val dateFormat: DateFormat = SimpleDateFormat("dd/MM/yyyy")
                val date = Date()
                return dateFormat.format(date)
            }
        val nextDate: String
            get() {
                val dateFormat: DateFormat = SimpleDateFormat("dd/MM/yyyy")
                val calendar = Calendar.getInstance()
                calendar.add(Calendar.DAY_OF_YEAR, 1)
                val tomorrow = calendar.time
                return dateFormat.format(tomorrow)
            }

        fun getIMEI(context: Context): String {
            val unique_id =
                Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
            Log.e("unique_id", "-->$unique_id")
            return unique_id
        }

        @SuppressLint("MissingPermission")
        fun internetChack(): Boolean {
            val ConnectionManager =
                MyApplication.mContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = ConnectionManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected == true
        }

        fun copyStream(`is`: InputStream, os: OutputStream) {
            val buffer_size = 1024
            try {
                val bytes = ByteArray(buffer_size)
                while (true) {
                    val count = `is`.read(bytes, 0, buffer_size)
                    if (count == -1) break
                    os.write(bytes, 0, count)
                }
            } catch (ex: Exception) {
                ex.toString()
            }
        }
    }
}