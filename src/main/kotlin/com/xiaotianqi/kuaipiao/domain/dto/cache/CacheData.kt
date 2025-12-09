package com.xiaotianqi.kuaipiao.domain.dto.cache

import java.time.Instant

data class CacheEntry(
    val key: String,
    val value: String,
    val type: String,
    val aiProvider: String,
    val operation: String,
    val confidence: Double?,
    val processingTime: Long,
    val expiresAt: Instant,
    val createdAt: Instant
)

data class CacheStats(
    val totalEntries: Long,
    val expiredEntries: Long,
    val entriesByProvider: Map<String, Long>,
    val cacheSize: Long,
    val additionalInfo: Map<String, String> = emptyMap()
)
