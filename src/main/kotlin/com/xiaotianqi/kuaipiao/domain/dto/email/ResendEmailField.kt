package com.xiaotianqi.kuaipiao.domain.dto.email

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode

data class ResendEmailRequest(
    val from: String,
    val to: List<String>,
    val subject: String,
    val html: String? = null,
    val text: String? = null,
    val cc: List<String>? = null,
    val bcc: List<String>? = null,
    @JsonProperty("reply_to")
    val replyTo: List<String>? = null,
    val template: ResendEmailTemplate? = null,
    val headers: Map<String, String>? = null,
    @JsonProperty("scheduled_at")
    val scheduledAt: String? = null,
    val attachments: List<ResendAttachmentRequest>? = null,
    val tags: List<ResendTag>? = null,
)

data class ResendEmailTemplate(
    val id: String,
    val variables: Map<String, JsonNode>? = null,
)

data class ResendAttachmentRequest(
    val content: String? = null,
    val filename: String? = null,
    val path: String? = null,
    @JsonProperty("content_type")
    val contentType: String? = null,
)

data class ResendTag(
    val name: String,
    val value: String,
)

data class ResendEmailResponse(
    val id: String,
)

data class ResendBatchEmailResponse(
    val data: List<ResendEmailResponse>,
)

data class ResendEmailDetail(
    val `object`: String,
    val id: String,
    val to: List<String>,
    val from: String,
    @JsonProperty("created_at")
    val createdAt: String,
    val subject: String,
    val html: String? = null,
    val text: String? = null,
    val bcc: List<String>? = null,
    val cc: List<String>? = null,
    @JsonProperty("reply_to")
    val replyTo: List<String>? = null,
    @JsonProperty("last_event")
    val lastEvent: String,
)