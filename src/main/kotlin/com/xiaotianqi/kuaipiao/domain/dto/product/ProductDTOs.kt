package com.xiaotianqi.kuaipiao.domain.dto.product

import com.xiaotianqi.kuaipiao.domain.dto.classification.AlternativeClassification
import java.time.Instant

data class ProductDto(
    val name: String,
    val description: String,
    val sku: String? = null,
    val brand: String? = null,
    val attributes: Map<String, String> = emptyMap(),
    val additionalInfo: Map<String, String> = emptyMap(),
    val category: String? = null,
    val accountingAccount: String? = null,
    val productCode: String? = null,
    val taxCategory: String? = null,
    val tariffCode: String? = null,
    val countrySpecific: Map<String, String> = emptyMap(),
    val confidence: Double? = null,
    val alternatives: List<AlternativeClassification> = emptyList(),
    val validationMessage: String? = null,
    val lastUpdated: Instant? = null
)

data class ProductRequest(
    val description: String,
    val category: String? = null
)

data class ProductClassificationData(
    val productName: String,
    val tariffCode: String,
    val suggestedCategory: String,
    val category: String? = null,
    val accountingAccount: String,
    val taxCategory: String,
    val productCode: String,
    val confidence: Double,
    val alternatives: List<AlternativeClassification> = emptyList(),
    val validationMessage: String? = null,
    val countrySpecific: Map<String, String> = emptyMap()
)

data class ProductBatchClassificationResult(
    val successful: List<ProductDto>,
    val failed: List<ProductClassificationError>,
    val summary: ProductClassificationSummary
)

data class ProductClassificationError(
    val productIndex: Int,
    val productName: String,
    val error: String
)

data class ProductClassificationSummary(
    val totalProducts: Int,
    val classified: Int,
    val failed: Int,
    val categories: Map<String, Int>,
    val averageConfidence: Double,
    val categoryDistribution: Map<String, Int>,
    val processingTime: Long
)

data class ProductSearchResult(
    val productName: String,
    val category: String,
    val taxCategory: String,
    val descriptions: List<String>,
    val sources: List<String>,
    val confidence: Double,
    val lastUpdated: Instant
) {
    companion object {
        fun empty(): ProductSearchResult {
            return ProductSearchResult(
                productName = "",
                category = "UNKNOWN",
                taxCategory = "UNKNOWN",
                descriptions = emptyList(),
                sources = emptyList(),
                confidence = 0.0,
                lastUpdated = Instant.now()
            )
        }
    }
}

data class TariffSearchResult(
    val productDescription: String,
    val suggestions: List<TariffSuggestion>,
    val sources: List<String>,
    val lastUpdated: Instant
) {
    companion object {
        fun empty(): TariffSearchResult {
            return TariffSearchResult(
                productDescription = "",
                suggestions = emptyList(),
                sources = emptyList(),
                lastUpdated = Instant.now()
            )
        }
    }
}

data class TariffSuggestion(
    val country: String,
    val code: String,
    val description: String,
    val confidence: Double
)
