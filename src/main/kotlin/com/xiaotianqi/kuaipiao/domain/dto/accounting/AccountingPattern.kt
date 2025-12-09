package com.xiaotianqi.kuaipiao.domain.dto.accounting

data class AccountingPattern(
    val vendorName: String,
    val accountCode: String,
    val description: String,
    val typicalAccounts: List<String>,
    val frequency: Int
)
