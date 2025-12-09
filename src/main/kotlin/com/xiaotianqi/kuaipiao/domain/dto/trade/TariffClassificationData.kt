package com.xiaotianqi.kuaipiao.domain.dto.trade

import com.xiaotianqi.kuaipiao.domain.dto.classification.AlternativeClassification
import com.xiaotianqi.kuaipiao.enums.RiskLevel
import java.time.Instant
import java.util.UUID

data class TariffClassification(
    val id: String = UUID.randomUUID().toString(),
    val productDescription: String,
    var tariffCode: String,
    var suggestedCategory: String,
    var accountingAccount: String,
    var taxCategory: String,
    var productCode: String,
    val tariffDescription: String,
    val confidence: Double,
    val countryOrigin: String,
    val countryDestination: String,
    var restrictions: TariffRestrictions? = null,
    var requiredDocuments: List<String> = emptyList(),
    val taxRate: Double? = null,
    var riskLevel: RiskLevel = RiskLevel.LOW,
    val alternatives: List<AlternativeClassification> = emptyList(),
    val legalReferences: List<String> = emptyList(),
    val lastUpdated: Instant = Instant.now()
)