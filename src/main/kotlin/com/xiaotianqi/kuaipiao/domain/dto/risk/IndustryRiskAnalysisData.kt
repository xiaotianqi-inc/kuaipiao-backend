package com.xiaotianqi.kuaipiao.domain.dto.risk

import com.xiaotianqi.kuaipiao.enums.RiskComparison

data class IndustryRiskAnalysis(
    val companyId: String,
    val industry: String,
    val companySize: String,
    val companyRiskScore: Double,
    val industryAverageRisk: Double,
    val riskComparison: RiskComparison,
    val recommendations: List<String>
)