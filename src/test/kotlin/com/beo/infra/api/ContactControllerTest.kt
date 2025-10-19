package com.beo.infra.api

import com.beo.action.ContactService
import com.beo.model.*
import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDate

@WebMvcTest(ContactController::class)
class ContactControllerWebTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper
) {

    @MockkBean
    private lateinit var contactService: ContactService

    private lateinit var contact: Contact

    @BeforeEach
    fun setup() {
        contact = Contact(
            firstName = "Madhan",
            lastName = "Kumar",
            birthDate = LocalDate.of(1992, 6, 25),
            addresses = mutableListOf(
                Address(
                    id = 1L,
                    street = "MG Road",
                    houseNumber = "12A",
                    postalCode = "641002",
                    city = "Coimbatore",
                    type = AddressType.PRIVATE
                )
            ),
            phoneNumbers = mutableListOf(
                PhoneNumber(
                    number = "+91-7200121685",
                    type = PhoneType.MOBILE_PRIVATE
                )
            )
        )
    }

    @Test
    fun `should create contact`() {
        //given
        every { contactService.createContact(any()) } returns contact

        //when
        mockMvc.perform(
            post("/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contact))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.firstName").value("Madhan"))
            .andExpect(jsonPath("$.lastName").value("Kumar"))

        //then
        verify { contactService.createContact(any()) }
    }

    @Test
    fun `should get all contacts`() {
        //given
        every { contactService.getAllContacts() } returns listOf(contact)

        //when
        mockMvc.perform(get("/contacts"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$[0].firstName").value("Madhan"))
            .andExpect(jsonPath("$[0].lastName").value("Kumar"))

        //then
        verify { contactService.getAllContacts() }
    }

    @Test
    fun `should get contact by id`() {
        //given
        every { contactService.getContactById(1L) } returns contact

        //when
        mockMvc.perform(get("/contacts/1"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.firstName").value("Madhan"))

        //then
        verify { contactService.getContactById(1L) }
    }

    @Test
    fun `should update contact`() {
        //given
        val updatedContact = contact.copy(firstName = "Arunesh")
        every { contactService.updateContact(1L, any()) } returns updatedContact

        //when
        mockMvc.perform(
            put("/contacts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedContact))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.firstName").value("Arunesh"))

        //then
        verify { contactService.updateContact(1L, any()) }
    }

    @Test
    fun `should delete contact`() {
        //given
        every { contactService.deleteContact(1L) } returns Unit

        //when
        mockMvc.perform(delete("/contacts/1"))
            .andExpect(status().isNoContent)

        //then
        verify { contactService.deleteContact(1L) }
    }

    @Test
    fun `should return 404 for missing contact`() {
        //given
        every { contactService.getContactById(99L) } returns null

        //when
        mockMvc.perform(get("/contacts/99"))
            .andExpect(status().isNotFound)

        //then
        verify { contactService.getContactById(99L) }
    }
}
