package com.xiaotianqi.kuaipiao.domain.dto.email

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode

data class ResendCreateWebhookRequest(
    val endpoint: String,
    val events: List<String>,
)

data class ResendWebhookResponse(
    val `object`: String,
    val id: String,
    @JsonProperty("signing_secret")
    val signingSecret: String,
)

data class ResendWebhookDetail(
    val `object`: String,
    val id: String,
    val endpoint: String,
    val events: List<String>? = null,
    val status: String,
    @JsonProperty("created_at")
    val createdAt: String,
    @JsonProperty("signing_secret")
    val signingSecret: String,
)

data class ResendWebhooksListResponse(
    val `object`: String,
    @JsonProperty("has_more")
    val hasMore: Boolean,
    val data: List<ResendWebhookDetail>,
)

data class CreateWebhookDTO(
    val type: String,
    val data: Map<String, JsonNode>? = null,
)
