package com.xiaotianqi.kuaipiao.domain.dto.processing

import com.xiaotianqi.kuaipiao.enums.FileType
import java.time.Instant

data class ProcessingHistoryItem(
    val id: String,
    val fileType: FileType,
    val documentType: String,
    val fileName: String,
    val confidence: Double,
    val processingTime: Long,
    val status: String,
    val createdAt: Instant = Instant.now()
)

data class ProcessingStats(
    val totalProcessed: Int,
    val successful: Int,
    val failed: Int,
    val averageConfidence: Double,
    val averageProcessingTime: Long
)