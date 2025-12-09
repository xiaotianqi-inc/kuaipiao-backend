package com.xiaotianqi.kuaipiao.domain.dto.financial

import com.fasterxml.jackson.databind.JsonNode

data class FinancialExtractionResult(
    val success: Boolean,
    val extractedData: Map<String, JsonNode>,
    val confidence: Double,
    val missingFields: List<String> = emptyList()
)
