package com.xiaotianqi.kuaipiao.domain.dto.sales

import com.xiaotianqi.kuaipiao.domain.dto.metrics.InventoryAlert
import com.xiaotianqi.kuaipiao.domain.dto.metrics.ProductGrowth
import java.time.Instant

data class SalesData(
    val date: String,
    val amount: Double,
    val product: String,
    val quantity: Int,
    val category: String
)


data class SalesPrediction(
    val companyId: String,
    val periodDays: Int,
    val predictedGrowth: Double,
    val topProducts: List<ProductGrowth>,
    val inventoryAlerts: List<InventoryAlert>,
    val riskFactors: List<String>,
    val confidence: Double,
    val period: String,
    val generatedAt: Instant = Instant.now(),
    val assumptions: List<String> = emptyList()
)
