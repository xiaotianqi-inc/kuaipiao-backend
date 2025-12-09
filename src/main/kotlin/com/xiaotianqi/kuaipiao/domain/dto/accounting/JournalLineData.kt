package com.xiaotianqi.kuaipiao.domain.dto.accounting

data class JournalLine(
    val accountCode: String,
    val accountName: String?,
    val description: String?,
    val debit: MoneyData? = null,
    val credit: MoneyData? = null,
    val dimensions: Map<String, String> = emptyMap()
)
