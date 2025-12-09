package com.xiaotianqi.kuaipiao.domain.dto.trade

import com.xiaotianqi.kuaipiao.domain.dto.tax.TaxDetail
import java.time.Instant

data class CalculationBreakdown(
    val itemNumber: Int,
    val productDescription: String,
    val duties: List<DutyCalculation>,
    val taxes: List<TaxDetail>,
    val total: Double
)

data class TradeAgreement(
    val name: String,
    val countries: List<String>,
    val effectiveDate: Instant,
    val tariffReductions: Map<String, Double>,
    val rulesOfOrigin: List<String>,
    val certificateRequirements: List<String>
)

data class TradeRules(
    val isTradeAllowed: Boolean,
    val requiresLicense: Boolean,
    val prohibitedProducts: List<String>
)
