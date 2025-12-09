package com.xiaotianqi.kuaipiao.domain.entity.company

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import java.time.Instant

@Entity
@Table(name = "companies")
data class Company(
    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    val id: String = "",

    @Column(nullable = false)
    val name: String,

    @Column(name = "tax_id", nullable = false, unique = true)
    val taxId: String,

    @Column
    val industry: String? = null,

    @Column(nullable = false)
    val street: String,

    @Column(nullable = false)
    val city: String,

    @Column(nullable = false)
    val state: String,

    @Column(name = "postal_code", nullable = false)
    val postalCode: String,

    @Column(nullable = false)
    val country: String,

    @Column(nullable = false)
    val phone: String,

    @Column(nullable = false)
    val email: String,

    @Column(name = "contact_person", nullable = false)
    val contactPerson: String,

    @Lob
    @Column(name = "previous_compliance_issues")
    val previousComplianceIssues: String? = null,

    @Column(name = "established_date")
    val establishedDate: Instant? = null,

    @Column(name = "created_at", nullable = false)
    val createdAt: Instant = Instant.now(),

    @Column(name = "updated_at")
    var updatedAt: Instant? = null
)
