package com.nipun.riceselling.model

data class PopularProductsModel(
    var limit: Any,
    var offset: Any,
    var products: ArrayList<Product>,
    var total_size: Int
)