package com.beo.infra.database

import jakarta.persistence.*

@Entity
@Table(name = "contacts")
data class ContactEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val firstName: String,

    @Column(nullable = false)
    val lastName: String,

    val birthDate: java.time.LocalDate? = null,

    @OneToMany(
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
        fetch = FetchType.EAGER
    )
    @JoinColumn(name = "contact_id")
    val addresses: MutableList<AddressEntity> = mutableListOf(),

    @OneToMany(
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
        fetch = FetchType.EAGER
    )
    @JoinColumn(name = "contact_id")
    val phoneNumbers: MutableList<PhoneNumberEntity> = mutableListOf()
)