package com.xiaotianqi.kuaipiao.domain.dto.predictions

import com.fasterxml.jackson.databind.JsonNode
import com.xiaotianqi.kuaipiao.domain.dto.document.DateRange
import com.xiaotianqi.kuaipiao.domain.dto.metrics.KeyFactor
import com.xiaotianqi.kuaipiao.domain.dto.metrics.PerformanceMetrics
import com.xiaotianqi.kuaipiao.domain.dto.metrics.TrendAnalysis
import com.xiaotianqi.kuaipiao.domain.dto.sales.SalesPrediction
import com.xiaotianqi.kuaipiao.enums.ModelType
import java.time.Instant

data class PredictionData(
    val label: String? = null,
    val category: String? = null,
    val confidence: Double,
    val explanation: String? = null,
    val tags: List<String> = emptyList(),
    val metadata: Map<String, String> = emptyMap(),
    val tariffCode: String? = null,
    val accountingAccount: String? = null,
    val taxCategory: String? = null,
    val productCode: String? = null
)


data class MultiplePeriodPrediction(
    val companyId: String,
    val predictions: List<SalesPrediction>,
    val consensus: PredictionConsensus
)

data class PredictionConsensus(
    val averageGrowth: Double,
    val confidence: Double,
    val agreementLevel: String
)


data class PredictionResult(
    val predictionId: String,
    val modelType: ModelType,
    val inputFeatures: Map<String, JsonNode>,
    val predictions: List<PredictionValue>,
    val confidence: Double,
    val explanation: PredictionExplanation,
    val metadata: PredictionMetadata,
    val createdAt: Instant = Instant.now()
)

data class PredictionValue(
    val target: String,
    val value: Double,
    val confidence: Double,
    val lowerBound: Double? = null,
    val upperBound: Double? = null,
    val unit: String? = null
)

data class PredictionExplanation(
    val keyFactors: List<KeyFactor>,
    val featureImportance: Map<String, Double>,
    val trendAnalysis: TrendAnalysis,
    val limitations: List<String> = emptyList()
)


data class PredictionMetadata(
    val modelVersion: String,
    val trainingDataRange: DateRange,
    val predictionHorizon: String,
    val algorithm: String,
    val hyperparameters: Map<String, JsonNode>,
    val performanceMetrics: PerformanceMetrics
)

data class PredictionRequest(
    val modelType: ModelType,
    val inputData: Map<String, JsonNode>,
    val horizon: String = "30d",
    val confidenceThreshold: Double = 0.7,
    val includeExplanation: Boolean = true,
    val includeUncertainty: Boolean = true
)


data class PredictionResponse(
    val success: Boolean,
    val prediction: PredictionResult? = null,
    val error: String? = null,
    val warnings: List<String> = emptyList(),
    val processingTime: Long
)