package com.xiaotianqi.kuaipiao.domain.dto.enterprise

import com.xiaotianqi.kuaipiao.enums.EntityStatus
import java.time.Instant

data class EnterpriseBackupData(
    val id: String,
    val enterpriseId: String,
    val description: String? = null,
    val backupPath: String,
    val size: Long,
    val status: EntityStatus = EntityStatus.PENDING,
    val includeData: Boolean = true,
    val includeSchema: Boolean = true,
    val createdAt: Instant,
    val completedAt: Instant? = null,
    val error: String? = null,
)


data class EnterpriseBackupCreateData(
    val enterpriseId: String,
    val description: String? = null,
    val backupPath: String,
    val includeData: Boolean = true,
    val includeSchema: Boolean = true,
    val createdAt: Instant,
)


data class EnterpriseBackupResponse(
    val id: String,
    val enterpriseId: String,
    val backupPath: String,
    val status: EntityStatus,
    val size: Long,
    val createdAt: Instant,
)
