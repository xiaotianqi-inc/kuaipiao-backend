package com.xiaotianqi.kuaipiao.domain.dto.accounting

import com.xiaotianqi.kuaipiao.domain.dto.invoice.InvoiceProcessingResult
import com.xiaotianqi.kuaipiao.domain.dto.validation.CrossValidationResult
import com.xiaotianqi.kuaipiao.enums.AutomationLevel

data class AccountingReconciliationResult(
    val extractedData: InvoiceProcessingResult,
    val reconciliation: AccountingReconciliation,
    val crossValidation: CrossValidationResult,
    val suggestedEntries: List<AccountingEntry>,
    val confidenceScore: Double,
    val automationLevel: AutomationLevel,
    val manualReviewRequired: Boolean = false
)