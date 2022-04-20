package com.nipun.riceselling.model

data class ProductDetailModel(
    var attributes: List<Any>,
    var capacity: Int,
    var category_ids: List<CategoryIdXX>,
    var choice_options: List<Any>,
    var created_at: String,
    var daily_needs: Int,
    var description: String,
    var discount: Int,
    var discount_type: String,
    var id: Int,
    var image: List<String>,
    var name: String,
    var price: Int,
    var rating: List<Any>,
    var status: Int,
    var tax: Int,
    var tax_type: String,
    var total_stock: Int,
    var translations: List<Any>,
    var unit: String,
    var updated_at: String,
    var variations: List<Any>,
    var wishlist_count: Int
)