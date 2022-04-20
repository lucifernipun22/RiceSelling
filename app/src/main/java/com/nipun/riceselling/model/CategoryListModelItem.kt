package com.nipun.riceselling.model

data class CategoryListModelItem(
    var created_at: String,
    var id: Int,
    var image: String,
    var name: String,
    var parent_id: Int,
    var position: Int,
    var status: Int,
    var translations: List<Any>,
    var updated_at: String
)