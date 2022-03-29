package com.nipun.riceselling

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.onesignal.OneSignal


class MyApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        var mContext: Context? = null
    }
    override fun onCreate() {
        super.onCreate()
        mContext = this
        OneSignal.startInit(this)
            .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
            .unsubscribeWhenNotificationsAreDisabled(true)
            .init()
    }


}