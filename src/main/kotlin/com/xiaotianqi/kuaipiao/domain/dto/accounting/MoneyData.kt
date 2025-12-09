package com.xiaotianqi.kuaipiao.domain.dto.accounting

data class MoneyData(
    val amount: Double,
    val currency: String,
    val exchangeRateToBase: Double = 1.0
) {
    val baseAmount: Double get() = amount * exchangeRateToBase
}