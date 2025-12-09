package com.xiaotianqi.kuaipiao.domain.entity.ai

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import java.time.Instant

@Entity
@Table(name = "ai_cache")
data class AiCache(
    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    val id: String = "",

    @Column(name = "cache_key", nullable = false, unique = true)
    val cacheKey: String,

    @Lob
    @Column(name = "cache_value", nullable = false)
    val cacheValue: String,

    @Column(name = "cache_type", nullable = false)
    val cacheType: String,

    @Column(name = "ai_provider", nullable = false)
    val aiProvider: String,

    @Column(nullable = false)
    val operation: String,

    @Column
    val confidence: Double? = null,

    @Column(name = "processing_time", nullable = false)
    val processingTime: Long,

    @Column(name = "expires_at", nullable = false)
    val expiresAt: Instant,

    @Column(name = "created_at", nullable = false)
    val createdAt: Instant
)