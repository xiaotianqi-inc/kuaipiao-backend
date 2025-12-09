package com.xiaotianqi.kuaipiao.domain.dto.metrics

import com.xiaotianqi.kuaipiao.enums.RiskLevel
import java.time.Instant

data class PerformanceMetrics(
    val mae: Double,
    val mse: Double,
    val rmse: Double,
    val r2: Double,
    val mape: Double,
    val lastUpdated: Instant = Instant.now()
)


data class ProductGrowth(
    val productName: String,
    val growth: Double,
    val currentSales: Double,
    val predictedSales: Double,
    val confidence: Double
)

data class InventoryAlert(
    val product: String,
    val risk: RiskLevel,
    val message: String,
    val suggestedAction: String? = null,
    val estimatedImpact: Double? = null
)

