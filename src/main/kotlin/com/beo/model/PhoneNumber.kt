package com.beo.model

data class PhoneNumber(
    val number: String,
    val type: PhoneType = PhoneType.MOBILE_PRIVATE
)

