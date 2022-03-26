package com.nipun.riceselling

import android.app.Application
import android.content.Context
import com.onesignal.OneSignal


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        mContext = this
        OneSignal.startInit(this)
            .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
            .unsubscribeWhenNotificationsAreDisabled(true)
            .init()
    }

    companion object {
        var mContext: Context? = null
    }
}