package com.xiaotianqi.kuaipiao.domain.dto.risk

import java.time.Instant

data class HighRiskCompany(
    val companyId: String,
    val riskScore: Double,
    val auditProbability: Double,
    val highRiskPatterns: Int,
    val lastAnalysis: Instant,
    val recommendedActions: List<String>
)


data class HighRiskTransaction(
    val transactionId: String,
    val date: Instant,
    val amount: Double,
    val description: String,
    val riskFactors: List<RiskFactor>,
    val suggestedAction: String
)
