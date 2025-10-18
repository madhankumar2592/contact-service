package com.beo.model

interface ContactRepository {
    fun save(contact: Contact): Contact
    fun findById(id: Long): Contact?
    fun findAll(): List<Contact>
    fun findByLastName(lastName: String): List<Contact>
    fun update(contact: Contact): Contact
    fun deleteById(id: Long)
}