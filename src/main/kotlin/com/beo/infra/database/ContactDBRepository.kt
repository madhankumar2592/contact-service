package com.beo.infra.database

import org.springframework.data.jpa.repository.JpaRepository

interface ContactDBRepository : JpaRepository<ContactEntity, Long> {
    fun findByLastNameStartsWithIgnoreCase(lastName: String): List<ContactEntity>
}