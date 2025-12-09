package com.xiaotianqi.kuaipiao.domain.dto.risk

import com.xiaotianqi.kuaipiao.enums.RiskPattern
import com.xiaotianqi.kuaipiao.enums.RiskType
import com.xiaotianqi.kuaipiao.enums.SeverityStatus
import java.time.Instant
data class RiskPrediction(
    val companyId: String,
    val riskType: RiskType,
    val riskScore: Double,
    val factors: List<RiskFactor>,
    val mitigationStrategies: List<String>,
    val monitoringRecommendations: List<String>,
    val predictionHorizon: String,
    val confidence: Double
)

data class RiskFactor(
    val factor: String,
    val impact: Double,
    val probability: Double,
    val timeFrame: String
)


data class RiskPattern(
    val patternType: RiskPattern,
    val description: String,
    val severity: SeverityStatus,
    val occurrences: Int,
    val totalAmount: Double,
    val firstOccurrence: Instant,
    val lastOccurrence: Instant
)