package com.beo.infra.database

import com.beo.model.AddressType
import jakarta.persistence.*

@Entity
@Table(name = "addresses")
data class AddressEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val street: String,

    @Column(nullable = false)
    val houseNumber: String,

    @Column(nullable = true)
    val postalCode: String? = null,

    @Column(nullable = true)
    val city: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val type: AddressType = AddressType.PRIVATE
)