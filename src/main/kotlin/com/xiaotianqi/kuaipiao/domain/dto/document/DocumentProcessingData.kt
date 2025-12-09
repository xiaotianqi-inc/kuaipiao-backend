package com.xiaotianqi.kuaipiao.domain.dto.document

import com.xiaotianqi.kuaipiao.domain.dto.invoice.InvoiceItemData
import com.xiaotianqi.kuaipiao.domain.dto.tax.TaxDetail
import com.xiaotianqi.kuaipiao.enums.DocumentType
import com.xiaotianqi.kuaipiao.enums.FileType

data class DocumentData(
    val documentId: String? = null,
    val invoiceNumber: String? = null,
    val issueDate: String? = null,
    val currency: String? = null,
    val emitterRuc: String? = null,
    val emitterName: String? = null,
    val receiverRuc: String? = null,
    val receiverName: String? = null,
    val subtotal: Double? = null,
    val taxAmount: Double? = null,
    val total: Double? = null,
    val taxes: List<TaxDetail> = emptyList(),
    val items: List<InvoiceItemData> = emptyList(),
    val country: String? = null,
    val rawContent: String? = null,
)

data class DocumentExtractionResult(
    val documentIndex: Int,
    val documentType: DocumentType,
    val success: Boolean,
    val confidence: Double,
    val rawText: String?,
    val extractedData: Map<String, Any?>,
    val processingTimeMs: Long
)

data class DocumentRequest(
    val fileBytes: ByteArray,
    val fileType: FileType,
    val documentType: DocumentType,
    val country: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DocumentRequest) return false
        if (!fileBytes.contentEquals(other.fileBytes)) return false
        if (fileType != other.fileType) return false
        if (documentType != other.documentType) return false
        if (country != other.country) return false

        return true
    }

    override fun hashCode(): Int {
        var result = fileBytes.contentHashCode()
        result = 31 * result + fileType.hashCode()
        result = 31 * result + documentType.hashCode()
        result = 31 * result + country.hashCode()
        return result
    }
}

data class BulkExtractionResult(
    val successful: List<DocumentExtractionResult>,
    val failed: List<ExtractionError>,
    val summary: ExtractionSummary
)

data class ExtractionError(
    val documentIndex: Int,
    val documentType: DocumentType,
    val error: String
)

data class ExtractionSummary(
    val totalDocuments: Int,
    val extracted: Int,
    val failed: Int,
    val totalFields: Int
)
