package com.xiaotianqi.kuaipiao.domain.dto.metrics

import java.time.Duration

data class AutomationMetrics(
    val companyId: String,
    val periodDays: Duration,
    val totalReconciliations: Int,
    val fullAutomation: Int,
    val partialAutomation: Int,
    val manualProcessing: Int,
    val averageConfidence: Double,
    val automationRate: Double,
    val timeSaved: Duration
)
