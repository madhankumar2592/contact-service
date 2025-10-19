package com.beo.action

import com.beo.model.Contact
import com.beo.model.ContactRepository
import org.springframework.stereotype.Service

@Service
class ContactService(
    private val contactRepository: ContactRepository
) {

    fun createContact(contact: Contact): Contact {
        validateAtLeastOneAddressOrPhoneIn(contact)
        return contactRepository.save(contact)
    }

    fun getAllContacts(): List<Contact> = contactRepository.findAll()

    fun getContactById(id: Long): Contact? = contactRepository.findById(id)

    fun findByLastName(lastName: String): List<Contact> =
        contactRepository.findByLastName(lastName)

    fun updateContact(id: Long, updatedContact: Contact): Contact {
        val existingContact = contactRepository.findById(id)
            ?: throw IllegalArgumentException("Contact with id $id not found")

        val contact = existingContact.copy(
            firstName = updatedContact.firstName,
            lastName = updatedContact.lastName,
            birthDate = updatedContact.birthDate,
            addresses = updatedContact.addresses,
            phoneNumbers = updatedContact.phoneNumbers
        )

        validateAtLeastOneAddressOrPhoneIn(contact)
        return contactRepository.update(contact)
    }

    fun deleteContact(id: Long) {
        contactRepository.deleteById(id)
    }

    private fun validateAtLeastOneAddressOrPhoneIn(contact: Contact) {
        val hasAddress = contact.addresses.isNotEmpty()
        val hasPhone = contact.phoneNumbers.isNotEmpty()

        if (!hasAddress && !hasPhone) {
            throw IllegalArgumentException("At least one address or phone number must be provided.")
        }
    }
}