package com.beo.infra.database

import com.beo.model.Contact
import com.beo.model.Address
import com.beo.model.PhoneNumber

import org.springframework.stereotype.Component

@Component
class ContactMapper {

    fun toEntity(domain: Contact): ContactEntity =
        ContactEntity(
            id = domain.id,
            firstName = domain.firstName,
            lastName = domain.lastName,
            birthDate = domain.birthDate,
            addresses = domain.addresses.map { addr ->
                AddressEntity(
                    id = addr.id,
                    street = addr.street,
                    houseNumber = addr.houseNumber,
                    postalCode = addr.postalCode,
                    city = addr.city,
                    type = addr.type
                )
            }.toMutableList(),
            phoneNumbers = domain.phoneNumbers.map { ph ->
                PhoneNumberEntity(
                    number = ph.number,
                    type = ph.type
                )
            }.toMutableList()
        )

    fun toDomain(entity: ContactEntity): Contact =
        Contact(
            id = entity.id!!,
            firstName = entity.firstName,
            lastName = entity.lastName,
            birthDate = entity.birthDate,
            addresses = entity.addresses.map { addr ->
                Address(
                    street = addr.street,
                    houseNumber = addr.houseNumber,
                    postalCode = addr.postalCode,
                    city = addr.city,
                    type = addr.type
                )
            }.toMutableList(),
            phoneNumbers = entity.phoneNumbers.map { ph ->
                PhoneNumber(
                    number = ph.number,
                    type = ph.type
                )
            }.toMutableList()
        )
}