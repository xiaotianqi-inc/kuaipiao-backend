package com.xiaotianqi.kuaipiao.client

import com.xiaotianqi.kuaipiao.domain.dto.ai.AnthropicMessage
import com.xiaotianqi.kuaipiao.domain.dto.ai.AnthropicRequest
import com.xiaotianqi.kuaipiao.domain.dto.ai.AnthropicResponse
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

class AnthropicClient(
    private val webClient: WebClient,
    private val model: String
) {
    suspend fun chat(prompt: String, maxTokens: Int = 1000): String {
        val request = AnthropicRequest(
            model = model,
            messages = listOf(AnthropicMessage("user", prompt)),
            maxTokens = maxTokens
        )

        val response = webClient.post()
            .uri("/messages")
            .bodyValue(request)
            .retrieve()
            .awaitBody<AnthropicResponse>()

        return response.content.first().text
    }
}