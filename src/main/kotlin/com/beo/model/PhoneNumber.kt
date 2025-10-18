package com.beo.model

data class PhoneNumber(
    val id: Long? = null,
    val number: String,
    val type: PhoneType = PhoneType.MOBILE_PRIVATE
)

