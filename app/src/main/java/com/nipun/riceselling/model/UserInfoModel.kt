package com.nipun.riceselling.model

data class UserInfoModel(
    var cm_firebase_token: Any,
    var created_at: String,
    var email: String,
    var email_verification_token: Any,
    var email_verified_at: Any,
    var f_name: String,
    var id: Int,
    var image: Any,
    var is_phone_verified: Int,
    var l_name: String,
    var phone: String,
    var temporary_token: String,
    var updated_at: String
)