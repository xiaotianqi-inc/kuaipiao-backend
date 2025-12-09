package com.xiaotianqi.kuaipiao.domain.dto.email

data class ResendCreateApiKeyRequest(
    val name: String,
    val permission: String = "full_access",
    val domain_id: String? = null,
)

data class ResendApiKeyResponse(
    val id: String,
    val token: String,
)

