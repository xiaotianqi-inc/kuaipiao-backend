package com.xiaotianqi.kuaipiao.domain.dto.tax

import com.xiaotianqi.kuaipiao.domain.dto.compliance.ComplianceRisk
import com.xiaotianqi.kuaipiao.enums.TaxType

data class TaxComplianceAnalysis(
    val isCompliant: Boolean,
    val applicableTaxes: List<TaxDetail>,
    val requiredDocuments: List<String>,
    val risks: List<ComplianceRisk>,
    val confidence: Double
)

data class TaxDetail(
    val type: String,
    val rate: Double,
    val taxableBase: Double? = null,
    val amount: Double? = null,
    val description: String? = null,
    val isExempt: Boolean = false,
    val isRetention: Boolean = false
)

data class TaxCalculationData(
    val subtotal: String,
    val taxRate: String,
    val taxAmount: String,
    val total: String,
    val breakdown: List<TaxBreakdownItem> = emptyList()
)
data class TaxBreakdownItem(
    val name: String,
    val rate: String,
    val amount: String,
    val type: TaxType
)