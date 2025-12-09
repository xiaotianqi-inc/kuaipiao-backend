package com.xiaotianqi.kuaipiao.domain.dto.compliance

import com.xiaotianqi.kuaipiao.domain.dto.document.DateRange
import com.xiaotianqi.kuaipiao.domain.dto.risk.HighRiskTransaction
import com.xiaotianqi.kuaipiao.domain.dto.risk.RiskPattern
import com.xiaotianqi.kuaipiao.domain.dto.transaction.TransactionData
import com.xiaotianqi.kuaipiao.domain.dto.validation.ValidationError
import com.xiaotianqi.kuaipiao.enums.SeverityStatus
import java.time.Instant

data class ComplianceRiskAnalysis(
    val companyId: String,
    val period: DateRange,
    val riskScore: Double,
    val riskPatterns: List<RiskPattern>,
    val highRiskTransactions: List<HighRiskTransaction>,
    val auditProbability: Double,
    val recommendations: List<ComplianceRecommendation>,
    val nextReviewDate: Instant,
    val analysisDate: Instant = Instant.now()
)

data class ComplianceRecommendation(
    val code: String,
    val description: String,
    val priority: SeverityStatus,
    val estimatedEffort: String,
    val suggestedDeadline: String? = null
)

data class ComplianceCheck(
    val isCompliant: Boolean,
    val riskScore: Double,
    val issues: List<String>?,
    val missingDocuments: List<String>,
    val validationErrors: List<ValidationError>,
    val warnings: List<ComplianceWarning>,
    val suggestedActions: List<String>
)

data class ComplianceWarning(
    val code: String,
    val message: String,
    val severity: SeverityStatus,
    val suggestedAction: String? = null
)

data class ComplianceRisk(
    val type: String,
    val severity: List<SeverityStatus> = listOf(SeverityStatus.HIGH),
    val description: String,
    val mitigation: String
)


data class ComplianceAnalysisRequest(
    val transactions: List<TransactionData>,
    val country: String,
    val timeWindow: TimeWindow,
    val riskScore: Double,
    val riskLevel: String,
    val recommendations: List<String>

)

data class TimeWindow(
    val startDate: String,
    val endDate: String
)
