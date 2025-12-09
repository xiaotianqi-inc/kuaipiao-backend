package com.xiaotianqi.kuaipiao.domain.dto.ai

import com.fasterxml.jackson.annotation.JsonProperty

data class ChatRequest(
    val model: String,
    val messages: List<ChatMessage>,
    val temperature: Double = 0.1
)

data class ChatMessage(
    val role: String,
    val content: String,
    val name: String? = null
)

data class Choice(
    val index: Int,
    val message: ChatMessage,
    @JsonProperty("finish_reason")
    val finishReason: String
)

data class ChatUsage(
    @JsonProperty("prompt_tokens")
    val promptTokens: Int,
    @JsonProperty("completion_tokens")
    val completionTokens: Int,
    @JsonProperty("total_tokens")
    val totalTokens: Int
)