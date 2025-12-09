package com.xiaotianqi.kuaipiao.domain.entity.ai

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import java.math.BigDecimal
import java.time.Instant

@Entity
@Table(name = "ai_model_results")
data class AiModelResult(
    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    val id: String = "",

    @Column(name = "model_type", nullable = false)
    val modelType: String,

    @Column(name = "ai_provider", nullable = false)
    val aiProvider: String,

    @Column(nullable = false)
    val operation: String,

    @Column(name = "input_hash", nullable = false)
    val inputHash: String,

    @Lob
    @Column(name = "input_data")
    val inputData: String? = null,

    @Lob
    @Column(name = "output_data", nullable = false)
    val outputData: String,

    @Column(nullable = false)
    val confidence: Double,

    @Column(name = "processing_time", nullable = false)
    val processingTime: Long,

    @Column(name = "tokens_used")
    val tokensUsed: Int? = null,

    @Column(nullable = true, precision = 10, scale = 6)
    val cost: BigDecimal? = null,

    @Column(nullable = false)
    val success: Boolean,

    @Lob
    @Column(name = "error_message")
    val errorMessage: String? = null,

    @Column(name = "created_at", nullable = false)
    val createdAt: Instant
)
