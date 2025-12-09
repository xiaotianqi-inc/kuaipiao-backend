package com.xiaotianqi.kuaipiao.domain.dto.organization

import com.fasterxml.jackson.databind.JsonNode
import com.xiaotianqi.kuaipiao.domain.dto.address.AddressData
import com.xiaotianqi.kuaipiao.domain.dto.document.DateRange
import com.xiaotianqi.kuaipiao.domain.dto.transaction.TransactionData
import com.xiaotianqi.kuaipiao.enums.AuditStatus
import java.time.Instant

data class CompanyInfo(
    val name: String,
    val taxId: String,
    val address: AddressData,
    val contact: ContactInfo,
    val createdAt: Instant,
    val updatedAt: Instant?
)

data class ContactInfo(
    val phone: String,
    val email: String,
    val contactPerson: String
)

data class CompanyHistory(
    val companyId: String,
    val fiscalYears: List<FiscalYearInfo> = emptyList(),
    val previousComplianceIssues: String? = null,
    val establishedDate: Instant? = null,
    val summary: String? = null
) {
    companion object {
        fun fromTransactions(
            companyId: String,
            transactions: List<TransactionData>,
            period: DateRange
        ): CompanyHistory {
            val totalRevenue = transactions.sumOf { it.amount }
            val summary = buildString {
                append("Analysis of ${transactions.size} transactions ")
                append("from ${period.start} to ${period.end}.")
                append("Total amount: $totalRevenue")
            }

            return CompanyHistory(
                companyId = companyId,
                fiscalYears = emptyList(),
                previousComplianceIssues = null,
                establishedDate = null,
                summary = summary
            )
        }
    }
}


data class FiscalYearInfo(
    val year: Int,
    val totalRevenue: Double,
    val totalExpenses: Double,
    val netIncome: Double,
    val auditStatus: AuditStatus
)

data class CountryRules(
    val currency: String,
    val taxRates: Map<String, JsonNode>,
    val requiredDocuments: List<String>
)