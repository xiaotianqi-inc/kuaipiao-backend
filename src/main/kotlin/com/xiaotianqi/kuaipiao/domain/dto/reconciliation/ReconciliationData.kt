package com.xiaotianqi.kuaipiao.domain.dto.reconciliation

import com.xiaotianqi.kuaipiao.domain.dto.accounting.AccountingReconciliationResult


data class BatchReconciliationResult(
    val successful: List<AccountingReconciliationResult>,
    val failed: List<ReconciliationError>,
    val summary: ReconciliationSummary
)

data class ReconciliationError(
    val documentIndex: Int,
    val documentType: String,
    val error: String
)

data class ReconciliationSummary(
    val totalDocuments: Int,
    val reconciled: Int,
    val failed: Int,
    val automationRate: Double
)

