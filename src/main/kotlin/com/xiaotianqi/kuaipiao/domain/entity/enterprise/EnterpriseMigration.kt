package com.xiaotianqi.kuaipiao.domain.entity.enterprise

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import java.time.Instant
import com.xiaotianqi.kuaipiao.enums.OperationStatus

@Entity
@Table(name = "enterprise_migrations")
data class EnterpriseMigration(
    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    val id: String = "",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enterprise_id", nullable = false)
    val enterprise: Enterprise,

    @Column(nullable = false)
    val version: String,

    @Lob
    @Column(nullable = false)
    val description: String,

    @Lob
    @Column(nullable = false)
    val script: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: OperationStatus,

    @Column(name = "applied_at", nullable = false)
    val appliedAt: Instant,

    @Column(name = "execution_time", nullable = false)
    val executionTime: Long,

    @Lob
    @Column(name = "error_message")
    val errorMessage: String? = null,

    @Column
    val checksum: String? = null
)
