package com.xiaotianqi.kuaipiao.domain.dto.provider

data class ProviderData(
    val name: String,
    val documentType: String,
    val documentNumber: String,
    val address: String?,
    val email: String?,
    val phone: String?
)