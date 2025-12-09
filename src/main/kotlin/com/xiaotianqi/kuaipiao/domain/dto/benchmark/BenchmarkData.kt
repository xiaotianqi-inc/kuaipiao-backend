package com.xiaotianqi.kuaipiao.domain.dto.benchmark

import com.xiaotianqi.kuaipiao.enums.RiskPattern

data class IndustryBenchmarks(
    val industry: String,
    val avgTransactionSize: Double,
    val typicalTransactionCount: Int,
    val commonRiskPatterns: List<RiskPattern>,
    val taxComplianceRate: Double = 0.95,
    val auditProbability: Double = 0.15
)