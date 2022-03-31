package com.nipun.riceselling.model

data class CouponModelItem(
    var code: String,
    var coupon_type: String,
    var created_at: String,
    var discount: Int,
    var discount_type: String,
    var expire_date: String,
    var id: Int,
    var limit: Int,
    var max_discount: Int,
    var min_purchase: Int,
    var start_date: String,
    var status: Int,
    var title: String,
    var updated_at: String
)