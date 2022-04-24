package com.nipun.riceselling.model

data class AddressModelItem(
    val address: String,
    val address_type: String,
    val contact_person_name: String,
    val contact_person_number: String,
    val created_at: String,
    val id: Int,
    val latitude: String,
    val longitude: String,
    val updated_at: String,
    val user_id: Int
)