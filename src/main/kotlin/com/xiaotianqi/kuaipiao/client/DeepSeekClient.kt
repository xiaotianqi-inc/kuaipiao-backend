package com.xiaotianqi.kuaipiao.client

import com.xiaotianqi.kuaipiao.domain.dto.ai.ChatMessage
import com.xiaotianqi.kuaipiao.domain.dto.ai.DeepSeekRequest
import com.xiaotianqi.kuaipiao.domain.dto.ai.DeepSeekResponse
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

class DeepSeekClient(
    private val webClient: WebClient,
    private val model: String
) {
    suspend fun chat(prompt: String): String {
        val request = DeepSeekRequest(
            model = model,
            messages = listOf(ChatMessage("user", prompt))
        )

        val response = webClient.post()
            .uri("/chat/completions")
            .bodyValue(request)
            .retrieve()
            .awaitBody<DeepSeekResponse>()

        return response.choices.first().message.content
    }
}