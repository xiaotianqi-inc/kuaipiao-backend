package com.xiaotianqi.kuaipiao.domain.dto.enterprise

import com.xiaotianqi.kuaipiao.enums.EnterprisePlan
import com.xiaotianqi.kuaipiao.enums.EntityStatus
import java.time.Instant

data class EnterpriseData(
    val id: String,
    val subdomain: String,
    val domain: String? = null,
    val status: EntityStatus = EntityStatus.ACTIVE,
    val plan: EnterprisePlan = EnterprisePlan.FREE,
    val settings: String?,
    val metadata: String?,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant? = null,
    val expiresAt: Instant? = null
)


data class EnterpriseCreateData(
    val id: String,
    val domain: String? = null,
    val subdomain : String,
    val status: EntityStatus = EntityStatus.ACTIVE,
    val plan: EnterprisePlan = EnterprisePlan.FREE,
    val settings: String,
    val metadata: String,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant? = null,
    val expiresAt: Instant? = null,
)


data class EnterpriseResponse(
    val id: String,
    val subdomain: String,
    val domain: String? = null,
    val status: EntityStatus = EntityStatus.ACTIVE,
    val plan: EnterprisePlan = EnterprisePlan.FREE,
    val settings: String?,
    val metadata: String?,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant? = null,
    val expiresAt: Instant? = null,
)
