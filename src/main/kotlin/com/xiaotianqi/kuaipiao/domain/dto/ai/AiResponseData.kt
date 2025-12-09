package com.xiaotianqi.kuaipiao.domain.dto.ai

import com.xiaotianqi.kuaipiao.enums.AiProvider

data class ChatResponse(
    val choices: List<Choice>,
    val provider: AiProvider,
    val content: String,
    val tokensUsed: Int? = null,
    val cached: Boolean = false,
    val processingTimeMs: Long,
    val confidence: Double? = null,
    val metadata: Map<String, String> = emptyMap()
)