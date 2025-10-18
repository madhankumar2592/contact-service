package com.beo.model

data class PhoneNumber(
    val id: Long? = null,
    val number: String,
    val type: PhoneType = PhoneType.MOBILE_PRIVATE
)

enum class PhoneType {
    LANDLINE_BUSINESS,
    LANDLINE_PRIVATE,
    MOBILE_BUSINESS,
    MOBILE_PRIVATE
}