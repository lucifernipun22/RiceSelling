package com.nipun.riceselling

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.preference.PreferenceManager
import com.google.gson.Gson

class SessionManager(context: Context?) {
    private val mPrefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    private var mEditor: Editor
    fun setStringData(key: String?, `val`: String?) {
        mEditor.putString(key, `val`)
        mEditor.commit()
    }

    fun getStringData(key: String?): String? {
        return mPrefs.getString(key, "")
    }

    fun setBooleanData(key: String?, `val`: Boolean?) {
        mEditor.putBoolean(key, `val`!!)
        mEditor.commit()
    }

    fun getBooleanData(key: String?): Boolean {
        return mPrefs.getBoolean(key, false)
    }


    fun setIntData(key: String?, `val`: Int) {
        mEditor.putInt(key, `val`)
        mEditor.commit()
    }

    fun getIntData(key: String?): Int {
        return mPrefs.getInt(key, 0)
    }

    fun logoutUser() {
        mEditor.clear()
        mEditor.commit()
    }

    companion object {
        var login = "login"
        var isopen = "isopen"
        var userdata = "Userdata"
        var address1 = "address"
        var iscart = false
        var area = "area"
        var currncy = "currncy"
        var privacy = "privacy_policy"
        var tremcodition = "tremcodition"
        var aboutUs = "about_us"
        var contactUs = "contact_us"
        var oMin = "o_min"
        var razKey = "raz_key"
        var tax = "tax"
        var CURRUNCY = "currncy"
        var COUPON = "coupon"
        var COUPONID = "couponid"
    }

    init {
        mEditor = mPrefs.edit()
    }
}