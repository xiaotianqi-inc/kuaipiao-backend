package com.xiaotianqi.kuaipiao.domain.dto.trade

import java.time.Instant

data class TariffCode(
    val code: String,
    val description: String,
    val parentCode: String? = null,
    val level: Int,
    val category: String,
    val unit: String? = null,
    val generalRate: Double,
    val preferentialRates: Map<String, Double> = emptyMap(),
    val restrictions: List<String> = emptyList(),
    val requiredDocuments: List<String> = emptyList(),
    val notes: String? = null,
    val lastUpdated: Instant,
    val validFrom: Instant,
    val validTo: Instant? = null
)

data class TariffRestrictions(
    val isRestricted: Boolean,
    val requiresLicense: Boolean,
    val quotaLimits: QuotaLimits? = null,
    val embargoCountries: List<String> = emptyList(),
    val specialRequirements: List<String> = emptyList()
)

data class TariffSearchRequest(
    val productDescription: String,
    val countryOrigin: String,
    val countryDestination: String,
    val includeRestrictions: Boolean = true,
    val includeRates: Boolean = true,
    val language: String = "es"
)

data class TariffSearchResponse(
    val success: Boolean,
    val classifications: List<TariffClassification>,
    val tradeAgreements: List<TradeAgreement>,
    val warnings: List<String>,
    val processingTime: Long
)

data class QuotaLimits(
    val quotaType: String,
    val limit: Double,
    val unit: String,
    val period: String,
    val used: Double = 0.0,
    val remaining: Double = 0.0
)

data class TariffClassificationRequest(
    val productDescription: String,
    val destinationCountry: String,
    val additionalContext: Map<String, String> = emptyMap()
)

data class TariffClassificationResponse(
    val hsCode: String,
    val description: String,
    val confidence: Double,
    val alternativeCodes: List<AlternativeCode> = emptyList(),
    val requiredDocuments: List<String>,
    val restrictions: List<String> = emptyList()
)

data class AlternativeCode(
    val hsCode: String,
    val description: String,
    val confidence: Double
)