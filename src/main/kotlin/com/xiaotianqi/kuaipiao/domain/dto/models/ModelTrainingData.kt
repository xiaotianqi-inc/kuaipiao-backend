package com.xiaotianqi.kuaipiao.domain.dto.models

import com.fasterxml.jackson.databind.JsonNode
import com.xiaotianqi.kuaipiao.domain.dto.metrics.PerformanceMetrics
import com.xiaotianqi.kuaipiao.enums.ModelType
import com.xiaotianqi.kuaipiao.enums.OperationStatus

data class ModelTrainingRequest(
    val modelType: ModelType,
    val trainingData: List<Map<String, JsonNode>>,
    val targetVariable: String,
    val features: List<String>,
    val validationSplit: Double = 0.2,
    val hyperparameters: Map<String, JsonNode> = emptyMap()
)


data class ModelTrainingResponse(
    val trainingId: String,
    val modelType: ModelType,
    val status: OperationStatus,
    val performance: PerformanceMetrics? = null,
    val trainingTime: Long,
    val modelVersion: String,
    val featureImportance: Map<String, Double>
)

data class ModelPerformance(
    val accuracy: Double,
    val precision: Double,
    val recall: Double,
    val f1Score: Double,
    val confusionMatrix: Map<String, Map<String, Int>>,
    val trainingTime: Long,
    val inferenceTime: Long,
    val modelSize: Long
)
