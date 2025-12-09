package com.xiaotianqi.kuaipiao.domain.dto.ai

import com.fasterxml.jackson.annotation.JsonProperty

data class OpenAiRequest(
    val model: String,
    val messages: List<ChatMessage>,
    val temperature: Double = 0.7,
    @JsonProperty("max_tokens")
    val maxTokens: Int? = null,
    val stream: Boolean = false
)

data class OpenAiResponse(
    val id: String,
    val choices: List<Choice>,
    val created: Long,
    val model: String,
    @JsonProperty("object")
    val objectType: String,
    val usage: ChatUsage
)

data class ClassificationResult(
    val category: String,
    val confidence: Double,
    val alternatives: List<String>
)

data class SentimentAnalysis(
    val sentiment: String,
    val confidence: Double,
    val aspects: List<String>,
    val emotions: List<String>
)

data class UsageStats(
    val totalTokens: Int = 0,
    val promptTokens: Int = 0,
    val completionTokens: Int = 0,
    val totalCost: Double = 0.0
)