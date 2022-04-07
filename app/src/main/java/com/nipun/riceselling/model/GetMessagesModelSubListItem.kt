package com.nipun.riceselling.model

data class GetMessagesModelSubListItem(
    var checked: Int,
    var created_at: String,
    var id: Int,
    var image: String,
    var message: String,
    var reply: String,
    var updated_at: String,
    var user_id: Int
)