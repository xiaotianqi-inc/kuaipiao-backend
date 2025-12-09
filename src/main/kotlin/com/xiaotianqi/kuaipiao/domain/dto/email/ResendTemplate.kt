package com.xiaotianqi.kuaipiao.domain.dto.email

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode

data class ResendCreateTemplateRequest(
    val name: String,
    val html: String,
    val alias: String? = null,
    val from: String? = null,
    val subject: String? = null,
    @JsonProperty("reply_to")
    val replyTo: List<String>? = null,
    val text: String? = null,
    val variables: List<ResendTemplateVariableInput>? = null,
)

data class ResendTemplateVariableInput(
    val key: String,
    val type: String,
    val variables: Map<String, JsonNode>? = null,
)

data class ResendTemplateVariable(
    val id: String,
    val key: String,
    val type: String,
    val variables: Map<String, JsonNode>? = null,
    @JsonProperty("created_at")
    val createdAt: String,
    @JsonProperty("updated_at")
    val updatedAt: String,
)

data class ResendTemplateResponse(
    val id: String,
    val `object`: String,
)

data class ResendTemplateDetail(
    val `object`: String,
    val id: String,
    @JsonProperty("current_version_id")
    val currentVersionId: String,
    val name: String,
    val alias: String? = null,
    val from: String? = null,
    val subject: String? = null,
    @JsonProperty("reply_to")
    val replyTo: List<String>? = null,
    val html: String,
    val text: String? = null,
    val variables: List<ResendTemplateVariable>? = null,
    @JsonProperty("created_at")
    val createdAt: String,
    @JsonProperty("updated_at")
    val updatedAt: String,
    val status: String,
    @JsonProperty("published_at")
    val publishedAt: String? = null,
    @JsonProperty("has_unpublished_versions")
    val hasUnpublishedVersions: Boolean,
)

data class ResendTemplateListItem(
    val id: String,
    val name: String,
    val status: String,
    @JsonProperty("published_at")
    val publishedAt: String? = null,
    @JsonProperty("created_at")
    val createdAt: String,
    @JsonProperty("updated_at")
    val updatedAt: String,
    val alias: String? = null,
)

data class ResendTemplatesListResponse(
    val `object`: String,
    val data: List<ResendTemplateListItem>,
    @JsonProperty("has_more")
    val hasMore: Boolean,
)
