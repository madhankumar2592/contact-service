package com.beo.infra.database

import com.beo.model.Contact
import com.beo.model.ContactRepository
import org.springframework.stereotype.Repository

@Repository
class ContactRepositoryImpl(
    private val contactDBRepository: ContactDBRepository,
    private val mapper: ContactMapper
) : ContactRepository {

    override fun save(contact: Contact): Contact {
        val contactEntity = mapper.toEntity(contact)
        val savedEntity = contactDBRepository.save(contactEntity)
        return mapper.toDomain(savedEntity)
    }

    override fun findById(id: Long): Contact? =
        contactDBRepository.findById(id)
            .map { mapper.toDomain(it) }
            .orElse(null)

    override fun findAll(): List<Contact> =
        contactDBRepository.findAll().map { mapper.toDomain(it) }

    override fun findByLastName(lastName: String): List<Contact> =
        contactDBRepository.findByLastNameStartsWithIgnoreCase(lastName)
            .map { mapper.toDomain(it) }

    override fun update(contact: Contact): Contact {
        val entity = mapper.toEntity(contact)
        val savedEntity = contactDBRepository.save(entity)
        return mapper.toDomain(savedEntity)
    }

    override fun deleteById(id: Long) {
        contactDBRepository.deleteById(id)
    }
}