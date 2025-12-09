package com.xiaotianqi.kuaipiao.client

import com.xiaotianqi.kuaipiao.domain.dto.ai.ChatMessage
import com.xiaotianqi.kuaipiao.domain.dto.ai.ChatRequest
import com.xiaotianqi.kuaipiao.domain.dto.ai.ChatResponse
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

class OpenAIClient(
    private val webClient: WebClient,
    private val model: String
) {

    suspend fun chat(messages: List<ChatMessage>, temperature: Double = 0.1): ChatResponse {
        val request = ChatRequest(
            model = model,
            messages = messages,
            temperature = temperature
        )

        return webClient.post()
            .uri("/chat/completions")
            .bodyValue(request)
            .retrieve()
            .awaitBody()
    }

    suspend fun extractInvoiceData(fileBase64: String, country: String): String {
        val prompt = """
            Extract invoice data from this document for country: $country
            Return JSON with: invoiceNumber, date, total, tax, items[], supplier, customer
            
            Document (base64): $fileBase64
        """.trimIndent()

        val response = chat(listOf(ChatMessage("user", prompt)))
        return response.choices.first().message.content
    }
}