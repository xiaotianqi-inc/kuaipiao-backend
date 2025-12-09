package com.xiaotianqi.kuaipiao.domain.dto.accounting

data class AccountingReconciliation(
    val documentType: String,
    val documentDate: String,
    val currency: String,
    val baseCurrency: String,
    val suggestedAccounts: List<AccountSuggestion>,
    val taxImplications: List<String> = emptyList(),
    val costCenter: String? = null,
    val projectCode: String? = null,
    val validationRules: List<String> = emptyList()
)