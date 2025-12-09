package com.xiaotianqi.kuaipiao.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import com.xiaotianqi.kuaipiao.client.OpenAIClient
import com.xiaotianqi.kuaipiao.client.DeepSeekClient
import com.xiaotianqi.kuaipiao.client.AnthropicClient

@Configuration
class AiConfig {

    @Bean
    fun openAIClient(
        @Value("\${ai.openai.api-key}") apiKey: String,
        @Value("\${ai.openai.base-url}") baseUrl: String,
        @Value("\${ai.openai.model}") model: String
    ): OpenAIClient {
        val webClient = WebClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader("Authorization", "Bearer $apiKey")
            .build()
        return OpenAIClient(webClient, model)
    }

    @Bean
    fun deepSeekClient(
        @Value("\${ai.deepseek.api-key}") apiKey: String,
        @Value("\${ai.deepseek.base-url}") baseUrl: String,
        @Value("\${ai.deepseek.model}") model: String
    ): DeepSeekClient {
        val webClient = WebClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader("Authorization", "Bearer $apiKey")
            .build()
        return DeepSeekClient(webClient, model)
    }

    @Bean
    fun anthropicClient(
        @Value("\${ai.anthropic.api-key}") apiKey: String,
        @Value("\${ai.anthropic.base-url}") baseUrl: String,
        @Value("\${ai.anthropic.model}") model: String
    ): AnthropicClient {
        val webClient = WebClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader("x-api-key", apiKey)
            .defaultHeader("anthropic-version", "2023-06-01")
            .build()
        return AnthropicClient(webClient, model)
    }
}