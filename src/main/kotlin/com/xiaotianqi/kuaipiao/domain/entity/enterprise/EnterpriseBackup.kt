package com.xiaotianqi.kuaipiao.domain.entity.enterprise

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import java.time.Instant
import com.xiaotianqi.kuaipiao.enums.EntityStatus

@Entity
@Table(name = "enterprise_backups")
data class EnterpriseBackup(
    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    val id: String = "",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enterprise_id", nullable = false)
    val enterprise: Enterprise,

    @Lob
    @Column
    val description: String? = null,

    @Column(name = "backup_path", nullable = false)
    val backupPath: String,

    @Column(nullable = false)
    val size: Long,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: EntityStatus,

    @Column(name = "include_data", nullable = false)
    val includeData: Boolean = true,

    @Column(name = "include_schema", nullable = false)
    val includeSchema: Boolean = true,

    @Column(name = "created_at", nullable = false)
    val createdAt: Instant,

    @Column(name = "completed_at")
    var completedAt: Instant? = null,

    @Lob
    @Column
    var error: String? = null
)
