package com.xiaotianqi.kuaipiao.domain.dto.accounting

import com.xiaotianqi.kuaipiao.domain.dto.validation.ValidationResult
import com.xiaotianqi.kuaipiao.enums.*
import java.time.Instant

data class AccountingEntriesResult(
    val entries: List<AccountingEntry>,
    val purpose: EntryPurpose,
    val validation: ValidationResult,
    val confidence: Double
)

data class AccountingEntry(
    val entryId: String,
    val journalId: String,
    val ledgerBook: LedgerBook,
    val type: List<AccountingType>,
    val accountCode: String,
    val accountName: String? = null,
    val debit: Double = 0.0,
    val credit: Double = 0.0,
    val currency: String,
    val amount: Double,
    val foreignAmount: Double? = null,
    val exchangeRate: Double = 1.0,
    val postingDate: String,
    val transactionDate: String,
    val reference: String? = null,
    val description: String? = null,
    val dimensions: AccountingDimension? = null,
    val provenance: String? = null,
    val confidence: Double = 1.0,
    val createdAt: Instant,
    val createdBy: String? = null
)

data class AccountSuggestion(
    val accountCode: String,
    val accountName: String,
    val confidence: Double,
    val reasoning: String,
    val amount: MoneyData,
    val description: String
)

data class AccountingDocument(
    val fileBytes: ByteArray,
    val fileType: FileType,
    val documentType: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AccountingDocument) return false

        if (!fileBytes.contentEquals(other.fileBytes)) return false
        if (fileType != other.fileType) return false
        if (documentType != other.documentType) return false

        return true
    }

    override fun hashCode(): Int {
        var result = fileBytes.contentHashCode()
        result = 31 * result + fileType.hashCode()
        result = 31 * result + documentType.hashCode()
        return result
    }
}
