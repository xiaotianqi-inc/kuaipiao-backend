package com.xiaotianqi.kuaipiao.domain.dto.organization

data class CompanySearchResult(
    val companyName: String,
    val country: String,
    val status: String,
    val taxId: String?,
    val registrationDate: String?,
    val sources: List<String>,
    val confidence: Double
) {
    companion object {
        fun empty(): CompanySearchResult {
            return CompanySearchResult(
                companyName = "",
                country = "",
                status = "UNKNOWN",
                taxId = null,
                registrationDate = null,
                sources = emptyList(),
                confidence = 0.0
            )
        }
    }
}