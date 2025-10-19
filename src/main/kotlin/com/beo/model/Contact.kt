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
        require(firstName.isNotBlank() && firstName.lowercase() != "null") {
            "First name is required."
        }
        require(lastName.isNotBlank() && lastName.lowercase() != "null") {
            "Last name is required."
        }


        require(addresses.isNotEmpty() && phoneNumbers.isNotEmpty()) {
            "At least one address and one phone number must be provided."
        }

        addresses.forEach { addr ->
            require(addr.street.isNotBlank() && addr.street.lowercase() != "null") {
                "Address street is required."
            }
            require(addr.houseNumber.isNotBlank() && addr.houseNumber.lowercase() != "null") {
                "Address house number is required."
            }
        }

        phoneNumbers.forEach { phone ->
            require(phone.number.isNotBlank() && phone.number.lowercase() != "null") {
                "Phone number is required."
            }
        }
    }

    fun addAddress(address: Address) = addresses.add(address)
    fun addPhoneNumber(phoneNumber: PhoneNumber) = phoneNumbers.add(phoneNumber)
}