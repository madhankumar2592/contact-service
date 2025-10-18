package com.beo.infra.database

import com.beo.model.PhoneType
import jakarta.persistence.*


@Entity
@Table(name = "phone_numbers")
data class PhoneNumberEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val number: String,

    @Enumerated(EnumType.STRING)
    val type: PhoneType = PhoneType.MOBILE_PRIVATE
)