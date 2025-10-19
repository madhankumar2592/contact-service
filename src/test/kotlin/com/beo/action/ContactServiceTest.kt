package com.beo.action

import com.beo.model.*
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

class ContactServiceTest {

    private lateinit var contactRepository: ContactRepository
    private lateinit var contactService: ContactService

    @BeforeEach
    fun setup() {
        contactRepository = mockk(relaxed = true)
        contactService = ContactService(contactRepository)
    }

    @Test
    fun `should add the contact`() {
        // given
        val contact = Contact(
            id = 1,
            firstName = "Arun",
            lastName = "Kumar",
            birthDate = LocalDate.of(1992, 6, 25),
            addresses = mutableListOf(Address(1,"MG Road", "12A", "Coimbatore", "PRIVATE")),
            phoneNumbers = mutableListOf(PhoneNumber("+91-7200121685", PhoneType.MOBILE_BUSINESS))
        )

        every { contactRepository.save(contact) } returns contact

        // when
        val actualContact = contactService.createContact(contact)

        // then
        verify(exactly = 1) { contactRepository.save(contact) }
        assertEquals("Arun", actualContact.firstName)
        assertEquals("Kumar", actualContact.lastName)
        assertEquals(PhoneType.MOBILE_BUSINESS, actualContact.phoneNumbers.first().type)
    }

    @Test
    fun `should get the list of contacts`() {
        // given
        val contact1 = Contact(
            id = 2,
            firstName = "Arun",
            lastName = "Kumar",
            phoneNumbers = mutableListOf(PhoneNumber("9999999999", PhoneType.MOBILE_BUSINESS)),
            addresses = mutableListOf(Address(1,"Main St", "10", "Coimbatore", "PRIVATE"))
        )
        val contact2 = Contact(
            id= 4,
            firstName = "Bala",
            lastName = "Ravi",
            phoneNumbers = mutableListOf(PhoneNumber("8888888888", PhoneType.MOBILE_BUSINESS)),
            addresses = mutableListOf(Address(1,"Anna Nagar", "23B", "Chennai", "WORK"))
        )

        every { contactRepository.findAll() } returns listOf(contact1, contact2)

        // when
        val actualContact = contactService.getAllContacts()

        // then
        assertEquals(2, actualContact.size)
        assertEquals("Arun", actualContact[0].firstName)
        assertEquals("Bala", actualContact[1].firstName)
        verify(exactly = 1) { contactRepository.findAll() }
    }

    @Test
    fun `should get contacts by last name`() {
        // given
        val contact = Contact(
            id= 3,
            firstName = "Arun",
            lastName = "Kumar",
            addresses = mutableListOf(Address(1,"MG Road", "12A", "Coimbatore", "PRIVATE")),
            phoneNumbers = mutableListOf(PhoneNumber("+91-7200121685", PhoneType.MOBILE_BUSINESS))
        )

        every { contactRepository.findByLastName("Kumar") } returns listOf(contact)

        // when
        val actualContact = contactService.findByLastName("Kumar")

        // then
        assertEquals(1, actualContact.size)
        assertEquals("Arun", actualContact.first().firstName)
        verify { contactRepository.findByLastName("Kumar") }
    }

    @Test
    fun `should not create contact without address or phone`() {
        // when + then
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Contact(
                id= 4,
                firstName = "NoAddress",
                lastName = "Guy",
                addresses = mutableListOf(),
                phoneNumbers = mutableListOf()
            )
        }

        assertEquals("At least one address and one phone number must be provided.", exception.message)
    }

    @Test
    fun `should delete contact`() {
        // given
        every { contactRepository.deleteById(1L) } returns Unit

        // when
        contactService.deleteContact(1L)

        // then
        verify { contactRepository.deleteById(1L) }
    }
}
