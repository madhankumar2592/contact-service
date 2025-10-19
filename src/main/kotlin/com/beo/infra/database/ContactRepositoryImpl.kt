package com.beo.infra.database

import com.beo.model.Contact
import com.beo.model.ContactRepository
import org.springframework.stereotype.Repository

@Repository
class ContactRepositoryImpl(
    private val contactJpaRepository: ContactJpaRepository,
    private val mapper: ContactMapper
) : ContactRepository {

    override fun save(contact: Contact): Contact {
        val contactEntity = mapper.toEntity(contact)
        val savedEntity = contactJpaRepository.save(contactEntity)
        return mapper.toDomain(savedEntity)
    }

    override fun findById(id: Long): Contact? =
        contactJpaRepository.findById(id)
            .map { mapper.toDomain(it) }
            .orElse(null)

    override fun findAll(): List<Contact> =
        contactJpaRepository.findAll().map { mapper.toDomain(it) }

    override fun findByLastName(lastName: String): List<Contact> =
        contactJpaRepository.findByLastNameStartsWithIgnoreCase(lastName)
            .map { mapper.toDomain(it) }

    override fun update(contact: Contact): Contact {
        val entity = mapper.toEntity(contact)
        val savedEntity = contactJpaRepository.save(entity)
        return mapper.toDomain(savedEntity)
    }

    override fun deleteById(id: Long) {
        contactJpaRepository.deleteById(id)
    }
}