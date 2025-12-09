package com.xiaotianqi.kuaipiao.service

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.stereotype.Service
import com.xiaotianqi.kuaipiao.client.OpenAIClient
import com.xiaotianqi.kuaipiao.client.DeepSeekClient
import com.xiaotianqi.kuaipiao.client.AnthropicClient
import com.xiaotianqi.kuaipiao.domain.dto.ai.ChatMessage
import com.xiaotianqi.kuaipiao.domain.dto.compliance.ComplianceAnalysisRequest
import com.xiaotianqi.kuaipiao.domain.dto.compliance.TimeWindow
import com.xiaotianqi.kuaipiao.domain.dto.invoice.InvoiceResult
import com.xiaotianqi.kuaipiao.domain.dto.product.ProductClassificationData
import java.util.Base64
import kotlin.String

@Service
class AiService(
    private val openAIClient: OpenAIClient,
    private val deepSeekClient: DeepSeekClient,
    private val anthropicClient: AnthropicClient
) {

    suspend fun processInvoice(fileBytes: ByteArray, country: String): InvoiceResult {
        val base64 = Base64.getEncoder().encodeToString(fileBytes)

        return coroutineScope {
            val openAIDeferred = async {
                runCatching { openAIClient.extractInvoiceData(base64, country) }
            }
            val deepSeekDeferred = async {
                runCatching { deepSeekClient.chat(buildInvoicePrompt(country)) }
            }

            openAIDeferred.await().getOrNull()
                ?: deepSeekDeferred.await().getOrNull()
                ?: throw Exception("All AI providers failed")
        }.let { parseInvoiceJson(it) }
    }

    suspend fun classifyProduct(description: String, category: String?): ProductClassificationData {
        val prompt = """
            Classify product for ERP:
            Product: $description
            Category: ${category ?: "Not specified"}
            
            Return JSON: {
                "category": "string",
                "accountCode": "string",
                "taxCategory": "string",
                "confidence": 0.0-1.0
            }
        """.trimIndent()

        val response = openAIClient.chat(listOf(
            ChatMessage("user", prompt)
        ))

        return parseProductJson(response.choices.first().message.content)
    }

    suspend fun analyzeCompliance(transactionData: String): ComplianceAnalysisRequest {
        val prompt = """
            Analyze compliance risk:
            $transactionData
            
            Return JSON: {
                "riskScore": 0-100,
                "riskLevel": "LOW|MEDIUM|HIGH",
                "recommendations": ["string"]
            }
        """.trimIndent()

        val response = anthropicClient.chat(prompt)
        return parseComplianceJson(response)
    }

    private fun buildInvoicePrompt(country: String) = """
        Extract invoice data for $country.
        Return valid JSON with all fields.
    """.trimIndent()

    private fun parseInvoiceJson(json: String): InvoiceResult {
        return InvoiceResult("", "", 0.0, "", emptyList())
    }

    private fun parseProductJson(json: String): ProductClassificationData {
        return ProductClassificationData(
            productName =" ",
            tariffCode = "",
            suggestedCategory = "",
            accountingAccount = "",
            category = "",
            taxCategory = "",
            confidence = 0.85,
            productCode = "",
            alternatives = emptyList(),
            validationMessage = "",
            countrySpecific = emptyMap(),
            )
    }

    private fun parseComplianceJson(json: String): ComplianceAnalysisRequest {
        // Debes proporcionar los 6 valores requeridos:
        return ComplianceAnalysisRequest(
            transactions = emptyList(),
            country = "MEDIUM",
            timeWindow = TimeWindow("", ""),
            riskScore = 0.0,
            riskLevel = "LOW",
            recommendations = emptyList()
        )
    }
}