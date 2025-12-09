package com.xiaotianqi.kuaipiao.domain.dto.document

import com.xiaotianqi.kuaipiao.domain.dto.classification.ClassificationItem
import com.xiaotianqi.kuaipiao.domain.dto.classification.ClassificationSummary
import com.xiaotianqi.kuaipiao.domain.dto.invoice.InvoiceProcessingResult
import com.xiaotianqi.kuaipiao.enums.FileType
import java.time.Instant

data class BatchProcessingResult(
    val successful: List<InvoiceProcessingResult>,
    val failed: List<ProcessingError>,
    val summary: ProcessingSummary
)

data class ProcessingError(
    val fileIndex: Int,
    val error: String,
    val fileType: FileType
)

data class ProcessingSummary(
    val totalFiles: Int,
    val processed: Int,
    val failed: Int,
    val averageConfidence: Double
)


data class BatchClassificationResult(
    val items: List<ClassificationItem>,
    val summary: ClassificationSummary,
    val processingMetadata: BatchProcessingMetadata
)


data class BatchProcessingMetadata(
    val batchId: String,
    val startedAt: Instant,
    val completedAt: Instant,
    val totalProcessingTime: Long,
    val itemsPerSecond: Double
)