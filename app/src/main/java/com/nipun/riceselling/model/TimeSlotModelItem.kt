package com.nipun.riceselling.model

data class TimeSlotModelItem(
    var created_at: String,
    var date: String,
    var end_time: String,
    var id: Int,
    var start_time: String,
    var status: Int,
    var updated_at: String
)