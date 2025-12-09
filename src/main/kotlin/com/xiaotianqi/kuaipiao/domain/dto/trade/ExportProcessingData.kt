package com.xiaotianqi.kuaipiao.domain.dto.trade

import com.xiaotianqi.kuaipiao.domain.dto.compliance.ComplianceCheck
import com.xiaotianqi.kuaipiao.domain.dto.invoice.InvoiceProcessingResult
import com.xiaotianqi.kuaipiao.enums.AlertType
import com.xiaotianqi.kuaipiao.enums.RiskLevel
import com.xiaotianqi.kuaipiao.enums.SeverityStatus
import java.time.Instant

data class ExportDocumentResult(
    val documentData: InvoiceProcessingResult,
    val tariffClassifications: List<TariffClassification>,
    val complianceCheck: ComplianceCheck,
    val requiredDocuments: List<String>,
    val alerts: List<ExportAlert>,
    val rawText: String,
    val riskLevel: RiskLevel,
    val processingTime: Long = 0L,
    val aiProvider: String = "Unknown",
    val timestamp: Instant = Instant.now()
)

data class ExportAlert(
    val type: AlertType,
    val severity: SeverityStatus,
    val message: String,
    val actionRequired: Boolean,
    val suggestedAction: String? = null
)

data class ExportRules(
    val originCountry: String,
    val destinationCountry: String,
    val isTradeAllowed: Boolean,
    val requiredDocuments: List<String>,
    val restrictions: List<String>,
    val taxTreaties: List<String>
)