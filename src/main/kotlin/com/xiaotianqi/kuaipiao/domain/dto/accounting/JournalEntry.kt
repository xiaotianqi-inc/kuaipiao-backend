package com.xiaotianqi.kuaipiao.domain.dto.accounting

import java.time.Instant
import com.xiaotianqi.kuaipiao.enums.LedgerBook

data class JournalEntry(
    val id: String,
    val ledger: LedgerBook,
    val date: Instant,
    val reference: String?,
    val description: String?,
    val lines: List<JournalLine>,
    val sourceDocumentType: String,
    val sourceDocumentId: String,
    val createdBy: String,
    val createdAt: Instant,
    val provenance: Map<String, String> = emptyMap()
) {
    fun balanceCheck(): Boolean {
        val totalDebit = lines.sumOf { it.debit?.baseAmount ?: 0.0 }
        val totalCredit = lines.sumOf { it.credit?.baseAmount ?: 0.0 }
        return kotlin.math.abs(totalDebit - totalCredit) < 0.000001
    }
}
