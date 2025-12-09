package com.xiaotianqi.kuaipiao.domain.dto.classification

import com.xiaotianqi.kuaipiao.domain.dto.predictions.PredictionData
import com.xiaotianqi.kuaipiao.enums.AiProvider
import com.xiaotianqi.kuaipiao.enums.ClassifierType
import com.xiaotianqi.kuaipiao.enums.ConfidentialityLevel
import com.xiaotianqi.kuaipiao.enums.OperationStatus
import com.xiaotianqi.kuaipiao.enums.UrgencyLevel
import java.time.Instant

data class ClassificationResult(
    val id: String,
    val input: ClassificationInput,
    val predictions: List<PredictionData>,
    val topPrediction: PredictionData,
    val confidence: Double,
    val modelVersion: String,
    val processingTime: Long,
    val metadata: ClassificationMetadata,
    val createdAt: Instant = Instant.now()
)

data class ClassificationInput(
    val text: String,
    val context: Map<String, String> = emptyMap(),
    val categories: List<String>? = null,
    val language: String = "es"
)

data class ClassificationMetadata(
    val classifierType: ClassifierType,
    val aiProvider: String,
    val modelName: String,
    val featureCount: Int? = null,
    val trainingDataSize: Int? = null,
    val threshold: Double = 0.5,
    val maxPredictions: Int = 5
)


data class ClassificationItem(
    val input: ClassificationInput,
    val result: ClassificationResult,
    val status: OperationStatus
)

data class ClassificationSummary(
    val totalItems: Int,
    val processed: Int,
    val failed: Int,
    val averageConfidence: Double,
    val categoryDistribution: Map<String, Int>,
    val processingTime: Long
)

data class ClassificationTrainingData(
    val features: List<Double>,
    val label: String,
    val weight: Double = 1.0,
    val metadata: Map<String, String> = emptyMap()
)

data class ClassificationError(
    val productName: String? = null,
    val reason: String,
    val details: String? = null,
    val provider: AiProvider,
    val operation: String = "classification",
    val index: Int,
    val input: String,
    val error: String
)

data class ExpenseClassification(
    val description: String,
    val expenseType: String,
    val category: String,
    val deductible: Boolean,
    val taxDeductionRate: Double? = null,
    val requiredDocumentation: List<String> = emptyList(),
    val confidence: Double,
    val legalReferences: List<String> = emptyList()
)

data class DocumentClassification(
    val documentType: String,
    val subType: String? = null,
    val urgency: UrgencyLevel,
    val retentionPeriod: Int? = null,
    val confidentiality: ConfidentialityLevel,
    val requiredActions: List<String> = emptyList(),
    val confidence: Double
)

data class AlternativeClassification(
    val tariffCode: String,
    val description: String,
    val confidence: Double,
    val reason: String
)