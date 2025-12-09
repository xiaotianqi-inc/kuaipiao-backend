package com.xiaotianqi.kuaipiao.domain.dto.organization

data class BusinessDataValidationRequest(
    val companyName: String,
    val taxId: String,
    val country: String,
    val dataType: String
)

data class BusinessDataValidationResult(
    val isValid: Boolean,
    val confidence: Double,
    val sources: List<String>,
    val warnings: List<String>
)