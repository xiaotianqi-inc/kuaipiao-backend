package com.xiaotianqi.kuaipiao.domain.entity.enterprise

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import java.time.Instant
import com.xiaotianqi.kuaipiao.enums.EntityStatus
import com.xiaotianqi.kuaipiao.enums.EnterprisePlan

@Entity
@Table(name = "enterprises")
data class Enterprise(
    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    val id: String = "",

    @Column(nullable = false)
    val subdomain: String,

    @Column
    val domain: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: EntityStatus,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val plan: EnterprisePlan,

    @Lob
    @Column
    val settings: String? = null,

    @Lob
    @Column
    val metadata: String? = null,

    @Column(name = "created_at", nullable = false)
    val createdAt: Instant = Instant.now(),

    @Column(name = "updated_at")
    var updatedAt: Instant? = null,

    @Column(name = "expires_at")
    var expiresAt: Instant? = null
)
