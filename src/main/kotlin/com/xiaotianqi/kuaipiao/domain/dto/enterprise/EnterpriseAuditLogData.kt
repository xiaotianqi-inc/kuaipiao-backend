package com.xiaotianqi.kuaipiao.domain.dto.enterprise

import java.time.Instant

data class EnterpriseAuditLogData(
    val id: String,
    val enterpriseId: String,
    val action: String,
    val actorId: String,
    val actorType: String,
    val details: String,
    val ipAddress: String? = null,
    val userAgent: String? = null,
    val createdAt: Instant,
)


data class EnterpriseAuditLogCreateData(
    val enterpriseId: String,
    val action: String,
    val actorId: String,
    val actorType: String,
    val details: String,
    val ipAddress: String? = null,
    val userAgent: String? = null,
    val createdAt: Instant = Instant.now(),
)


data class EnterpriseAuditLogResponse(
    val id: String,
    val enterpriseId: String,
    val action: String,
    val actorId: String,
    val actorType: String,
    val createdAt: Instant = Instant.now(),
)
