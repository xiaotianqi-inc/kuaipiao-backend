package com.xiaotianqi.kuaipiao.domain.dto.ai

import com.fasterxml.jackson.annotation.JsonProperty

data class AnthropicRequest(
    val model: String,
    val messages: List<AnthropicMessage>,
    @JsonProperty("max_tokens")
    val maxTokens: Int,
)

data class AnthropicMessage(
    val role: String,
    val content: String
)

data class AnthropicResponse(
    val id: String,
    val type: String,
    val role: String,
    val content: List<AnthropicContent>,
    val model: String,
    @JsonProperty("stop_reason")
    val stopReason: String? = null,
    val usage: AnthropicUsage
)

data class AnthropicContent(
    val type: String,
    val text: String
)

data class AnthropicUsage(
    @JsonProperty("input_tokens")
    val inputTokens: Int,
    @JsonProperty("output_tokens")
    val outputTokens: Int
)