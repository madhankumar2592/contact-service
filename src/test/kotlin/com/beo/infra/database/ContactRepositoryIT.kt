package com.beo.infra.database

import com.beo.model.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Transactional
@SpringBootTest
@ActiveProfiles("test")
class ContactRepositoryIT @Autowired constructor(
    private val contactRepositoryImpl: ContactRepositoryImpl
) {

    @Test
    fun `should save and retrieve contact`() {
        //given
        val contact = buildContact(firstName = "Arun", lastName = "Kumar", phoneNumber = "+91-7200121685")

        //when
        val actualContact = contactRepositoryImpl.save(contact)

        //then
        val expectedContact = contactRepositoryImpl.findById(actualContact.id!!)

        assertNotNull(expectedContact?.id)
        assertEquals("Arun", expectedContact!!.firstName)
        assertEquals("Kumar", expectedContact.lastName)
        assertEquals("+91-7200121685", expectedContact.phoneNumbers.first().number)
    }

    @Test
    fun `should update existing contact`() {
        //given
        val contact = contactRepositoryImpl.save(buildContact(firstName = "Bala", lastName = "Ravi"))
        val updated = contact.copy(firstName = "Balaji")


        //when
        val actualContact = contactRepositoryImpl.update(updated)

        //then
        val expectedContact = contactRepositoryImpl.findById(actualContact.id!!)

        assertEquals("Balaji", expectedContact!!.firstName)
    }

    @Test
    fun `should delete contact by id`() {
        //given
        val contact = contactRepositoryImpl.save(buildContact(firstName = "Madhan", lastName = "Kumar"))

        //when
        contactRepositoryImpl.deleteById(contact.id!!)

        //then
        val expectedContact = contactRepositoryImpl.findById(contact.id!!)

        assertNull(expectedContact)
    }

    @Test
    fun `should list all contacts`() {
        contactRepositoryImpl.save(buildContact(firstName = "Arun", lastName = "Kumar"))
        contactRepositoryImpl.save(buildContact(firstName = "Bala", lastName = "Ravi"))

        val expectedContact = contactRepositoryImpl.findAll()

        assertEquals(2, expectedContact.size)
        assertTrue(expectedContact.any { it.firstName == "Arun" && it.lastName == "Kumar" })
        assertTrue(expectedContact.any { it.firstName == "Bala" && it.lastName == "Ravi" })
    }

    private fun buildContact(
        firstName: String = "Default",
        lastName: String = "User",
        birthDate: LocalDate? = LocalDate.of(1990, 1, 1),
        street: String = "Default Street",
        houseNumber: String = "1A",
        postalCode: String = "000000",
        city: String = "Coimbatore",
        addressType: AddressType = AddressType.PRIVATE,
        phoneNumber: String = "9999999999",
        phoneType: PhoneType = PhoneType.MOBILE_PRIVATE
    ): Contact {
        return Contact(
            firstName = firstName,
            lastName = lastName,
            birthDate = birthDate,
            addresses = mutableListOf(
                Address(
                    street = street,
                    houseNumber = houseNumber,
                    postalCode = postalCode,
                    city = city,
                    type = addressType
                )
            ),
            phoneNumbers = mutableListOf(
                PhoneNumber(
                    number = phoneNumber,
                    type = phoneType
                )
            )
        )
    }
}
