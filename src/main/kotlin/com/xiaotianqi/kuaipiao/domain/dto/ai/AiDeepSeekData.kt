package com.xiaotianqi.kuaipiao.domain.dto.ai

import com.fasterxml.jackson.annotation.JsonProperty 

data class DeepSeekRequest(
    val model: String,
    val messages: List<ChatMessage>,
    val temperature: Double = 0.1,
    @JsonProperty("max_tokens")
    val maxTokens: Int? = null,
    val stream: Boolean = false
)

data class DeepSeekResponse(
    val id: String,
    val choices: List<Choice>,
    val created: Long,
    val model: String,
    @JsonProperty("object")
    val objectType: String,
    val usage: ChatUsage? = null
)
