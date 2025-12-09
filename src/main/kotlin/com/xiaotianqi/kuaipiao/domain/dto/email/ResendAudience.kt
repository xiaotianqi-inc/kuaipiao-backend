package com.xiaotianqi.kuaipiao.domain.dto.email

data class ResendCreateAudienceRequest(
    val name: String,
)

data class ResendAudienceResponse(
    val id: String,
    val `object`: String,
    val name: String,
)

data class ResendAudienceListItem(
    val id: String,
    val name: String,
    val created_at: String,
)

data class ResendAudiencesListResponse(
    val `object`: String,
    val data: List<ResendAudienceListItem>,
)

