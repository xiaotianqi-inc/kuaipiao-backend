package com.xiaotianqi.kuaipiao.domain.dto.metrics

import com.xiaotianqi.kuaipiao.enums.TrendDirection

data class KeyFactor(
    val factor: String,
    val impact: Double,
    val explanation: String,
    val evidence: List<String> = emptyList()
)

data class TrendAnalysis(
    val direction: TrendDirection,
    val strength: Double,
    val duration: String,
    val confidence: Double
)