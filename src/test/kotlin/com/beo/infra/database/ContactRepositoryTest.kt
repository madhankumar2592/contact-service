/*
package com.beo.infra.database

import com.beo.model.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDate

@DataJpaTest
@ActiveProfiles("test")
class ContactRepositoryTest @Autowired constructor(
    val contactRepository: ContactRepository
) {

    @Test
    fun `should save and retrieve contact with phone and address`() {
        // Arrange
        val address = Address(
            street = "Race Course Road",
            houseNumber = "45B",
            city = "Coimbatore",
            type = AddressType.BUSINESS
        )
        val phone = PhoneNumber(number = "9876543210")
        val contact = Contact(
            firstName = "Madhan",
            lastName = "Kumar",
            birthDate = LocalDate.of(1995, 1, 16),
            addresses = mutableListOf(address),
            phoneNumbers = mutableListOf(phone)
        )

        // Act
        val actualContact = contactRepository.save(contact)
        val expectedContact : Contact? = contactRepository.findById(actualContact.id!!)

        // Assert
        assertEquals("Madhan", expectedContact?.firstName)
        assertEquals("Kumar", expectedContact?.lastName)
        assertEquals(1, expectedContact?.addresses?.size)
        assertEquals("Race Course Road", expectedContact?.addresses?.first()?.street)
        assertEquals(1, expectedContact?.phoneNumbers?.size)
        assertEquals("9876543210", expectedContact?.phoneNumbers?.first()?.number)
    }

    @Test
    fun `should update contact by adding a new phone number`() {
        // Arrange
        val contact = Contact(
            firstName = "Madhan",
            lastName = "Kumar",
            phoneNumbers = mutableListOf(PhoneNumber(number = "1234567890"))
        )
        val expectedContact = contactRepository.save(contact)

        // Act
        val newPhone = PhoneNumber(number = "1112223333")
        expectedContact.addPhoneNumber(newPhone)
        val updated = contactRepository.save(expectedContact)
        val actualContact = contactRepository.findById(updated.id!!)

        // Assert
        assertEquals(2, actualContact?.phoneNumbers?.size)
        actualContact?.phoneNumbers?.any { it.number == "1112223333" }?.let { assertTrue(it) }
    }
}*/
