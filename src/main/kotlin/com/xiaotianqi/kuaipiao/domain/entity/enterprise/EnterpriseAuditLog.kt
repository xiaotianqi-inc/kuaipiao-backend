package com.xiaotianqi.kuaipiao.domain.entity.enterprise

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import java.time.Instant

@Entity
@Table(name = "enterprise_audit_logs")
data class EnterpriseAuditLog(
    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    val id: String = "",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enterprise_id", nullable = false)
    val enterprise: Enterprise,

    @Column(nullable = false)
    val action: String,

    @Column(name = "actor_id", nullable = false)
    val actorId: String,

    @Column(name = "actor_type", nullable = false)
    val actorType: String,

    @Lob
    @Column(nullable = false)
    val details: String,

    @Column(name = "ip_address")
    val ipAddress: String? = null,

    @Lob
    @Column(name = "user_agent")
    val userAgent: String? = null,

    @Column(name = "created_at", nullable = false)
    val createdAt: Instant
)
