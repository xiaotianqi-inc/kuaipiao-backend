package com.xiaotianqi.kuaipiao.domain.dto.organization

import com.xiaotianqi.kuaipiao.enums.EntityStatus
import java.time.Instant

data class OrganizationData(
    val id: String,
    val userIds: List<String> = emptyList(),
    val name: String,
    val code: String,
    val address: String,
    val phone: String,
    val email: String,
    val country: String,
    val city: String,
    val status: EntityStatus = EntityStatus.ACTIVE,
    val metadata: String?,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant? = null,
)


data class OrganizationCreateData(
    val id: String,
    val userIds: List<String> = emptyList(),
    val name: String,
    val code: String,
    val address: String,
    val phone: String,
    val email: String,
    val country: String,
    val city: String,
    val metadata: String?,
    val status: EntityStatus = EntityStatus.ACTIVE,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant? = null,
)


data class OrganizationResponse(
    val id: String,
    val userIds: List<String> = emptyList(),
    val name: String,
    val code: String,
    val address: String,
    val phone: String,
    val email: String,
    val country: String,
    val city: String,
    val metadata: String?,
    val status: EntityStatus = EntityStatus.ACTIVE,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant? = null,
)

data class OrganizationRequest(
    val name: String,
    val code: String,
    val address: String,
    val phone: String,
    val email: String,
    val country: String,
    val city: String,
    val metadata: String? = null,
    val status: EntityStatus = EntityStatus.ACTIVE
)

data class UpdateStatusRequest(
    val status: EntityStatus
)
