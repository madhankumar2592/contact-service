package com.beo.model

data class Address(
    val id: Long? = null,
    val street: String,
    val houseNumber: String,
    val postalCode: String? = null,
    val city: String? = null,
    val type: AddressType = AddressType.PRIVATE
)

enum class AddressType {
    BUSINESS,
    PRIVATE
}
