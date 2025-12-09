package com.xiaotianqi.kuaipiao.domain.dto.email

data class ResendCreateContactRequest(
    val email: String,
    val first_name: String? = null,
    val last_name: String? = null,
    val unsubscribed: Boolean = false,
)

data class ResendContactResponse(
    val `object`: String,
    val id: String,
)

data class ResendContactDetail(
    val `object`: String,
    val id: String,
    val email: String,
    val first_name: String? = null,
    val last_name: String? = null,
    val created_at: String,
    val unsubscribed: Boolean,
)

data class ResendContactsListResponse(
    val `object`: String,
    val data: List<ResendContactDetail>,
)