package com.xiaotianqi.kuaipiao.domain.dto.email

import kotlinx.serialization.Serializable

data class SendEmailDTO(
    val from: String? = null,
    val to: List<String>,
    val subject: String,
    val html: String? = null,
    val text: String? = null,
    val cc: List<String>? = null,
    val bcc: List<String>? = null,
    val replyTo: List<String>? = null,
    val tags: List<Map<String, String>>? = null,
)

data class SendBatchEmailDTO(
    val emails: List<SendEmailDTO>,
)

data class CreateTemplateDTO(
    val name: String,
    val html: String,
    val alias: String? = null,
    val from: String? = null,
    val subject: String? = null,
    val replyTo: List<String>? = null,
    val text: String? = null,
)

data class CreateAudienceDTO(
    val name: String,
)

data class CreateContactDTO(
    val email: String,
    val firstName: String? = null,
    val lastName: String? = null,
)


@Serializable
data class DomainResponse(
    val id: String,
    val name: String,
    val status: String,
    val records: List<DnsRecord>? = null
)

@Serializable
data class DnsRecord(
    val type: String,
    val name: String,
    val value: String
)