package com.xiaotianqi.kuaipiao.domain.dto.tax

import java.time.Instant

data class TaxRegulationSearchResult(
    val country: String,
    val taxType: String,
    val regulations: List<TaxRegulation>,
    val lastUpdated: Instant
) {
    companion object {
        fun empty(): TaxRegulationSearchResult {
            return TaxRegulationSearchResult(
                country = "",
                taxType = "",
                regulations = emptyList(),
                lastUpdated = Instant.now()
            )
        }
    }
}

data class TaxRegulation(
    val name: String,
    val description: String,
    val effectiveDate: String,
    val authority: String
)


data class RegulatoryChange(
    val country: String,
    val description: String,
    val effectiveDate: Instant,
    val impactLevel: String
)