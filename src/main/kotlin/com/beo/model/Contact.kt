package com.beo.model

import java.time.LocalDate

data class Contact(
    val id: Long? = null,
    val firstName: String,
    val lastName: String,
    val birthDate: LocalDate? = null,
    val addresses: MutableList<Address> = mutableListOf(),
    val phoneNumbers: MutableList<PhoneNumber> = mutableListOf()
) {
    init {
        require(firstName.isNotBlank()) { "First name is required." }
        require(lastName.isNotBlank()) { "Last name is required." }

        val hasAddress = addresses.isNotEmpty()
        val hasPhone = phoneNumbers.isNotEmpty()
        require(hasAddress || hasPhone) {
            "At least one address or phone number must be provided."
        }

        addresses.forEach { addr ->
            require(addr.street.isNotBlank()) { "Address street is required." }
            require(addr.houseNumber.isNotBlank()) { "Address house number is required." }
        }

        phoneNumbers.forEach { phone ->
            require(phone.number.isNotBlank()) { "Phone number is required." }
        }
    }

    fun addAddress(address: Address) = addresses.add(address)
    fun addPhoneNumber(phoneNumber: PhoneNumber) = phoneNumbers.add(phoneNumber)
}