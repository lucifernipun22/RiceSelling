package com.nipun.riceselling.model

data class UserInfoModel(
    var cm_firebase_token: String?,
    var created_at: String?,
    var email: String?,
    var email_verification_token: String?,
    var email_verified_at: String?,
    var f_name: String?,
    var id: Int?,
    var image: String?,
    var is_phone_verified: Int?,
    var l_name: String?,
    var phone: String?,
    var temporary_token: String?,
    var updated_at: String?
)