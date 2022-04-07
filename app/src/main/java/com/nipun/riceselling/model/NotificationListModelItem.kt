package com.nipun.riceselling.model

data class NotificationListModelItem(
    var created_at: String,
    var description: String,
    var id: Int,
    var image: Any,
    var status: Int,
    var title: String,
    var updated_at: String
)