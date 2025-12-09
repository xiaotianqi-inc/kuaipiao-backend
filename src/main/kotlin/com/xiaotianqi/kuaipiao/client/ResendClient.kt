package com.xiaotianqi.kuaipiao.client

import com.xiaotianqi.kuaipiao.domain.dto.email.ResendBatchEmailResponse
import com.xiaotianqi.kuaipiao.domain.dto.email.ResendDomainResponse
import com.xiaotianqi.kuaipiao.domain.dto.email.ResendEmailRequest
import com.xiaotianqi.kuaipiao.domain.dto.email.ResendEmailResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Component
class ResendClient(
    @Qualifier("resendWebClient") private val webClient: WebClient
) {
    suspend fun sendEmail(request: ResendEmailRequest): String? {
        return try {
            val response = webClient.post()
                .uri("/emails")
                .bodyValue(request)
                .retrieve()
                .awaitBody<ResendEmailResponse>()
            response.id
        } catch (e: Exception) {
            null
        }
    }

    suspend fun sendBatchEmails(requests: List<ResendEmailRequest>): List<String>? {
        return try {
            val response = webClient.post()
                .uri("/emails/batch")
                .bodyValue(requests)
                .retrieve()
                .awaitBody<ResendBatchEmailResponse>()
            response.data.map { it.id }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun createDomain(name: String, region: String = "us-east-1"): ResendDomainResponse? {
        return try {
            webClient.post()
                .uri("/domains")
                .bodyValue(mapOf("name" to name, "region" to region))
                .retrieve()
                .awaitBody()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun verifyDomain(domainId: String): Boolean {
        return try {
            webClient.post()
                .uri("/domains/$domainId/verify")
                .retrieve()
                .awaitBody<Unit>()
            true
        } catch (e: Exception) {
            false
        }
    }
}