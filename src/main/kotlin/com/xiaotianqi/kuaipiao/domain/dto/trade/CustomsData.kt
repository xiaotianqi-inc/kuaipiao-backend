package com.xiaotianqi.kuaipiao.domain.dto.trade

import com.xiaotianqi.kuaipiao.domain.dto.organization.CompanyInfo
import com.xiaotianqi.kuaipiao.domain.dto.tax.TaxDetail
import com.xiaotianqi.kuaipiao.enums.CustomsStatus
import java.time.Instant

data class CustomsDeclaration(
    val declarationNumber: String,
    val exporter: CompanyInfo,
    val importer: CompanyInfo,
    val items: List<CustomsItem>,
    val totalValue: Double,
    val totalWeight: Double,
    val incoterm: String,
    val transportMode: String,
    val departureDate: Instant,
    val arrivalDate: Instant? = null,
    val customsValue: Double,
    val duties: List<DutyCalculation>,
    val taxes: List<TaxDetail>,
    val status: CustomsStatus
)

data class CustomsItem(
    val itemNumber: Int,
    val productDescription: String,
    val tariffCode: String,
    val quantity: Double,
    val unit: String,
    val unitValue: Double,
    val totalValue: Double,
    val weight: Double,
    val originCountry: String,
    val preferenceCriteria: String? = null
)

data class DutyCalculation(
    val type: String,
    val base: Double,
    val rate: Double,
    val amount: Double,
    val exemption: Double = 0.0,
    val payable: Double
)

data class CustomsCalculationRequest(
    val items: List<CustomsItem>,
    val incoterm: String,
    val transportMode: String,
    val importerCountry: String,
    val exporterCountry: String
)


data class CustomsCalculationResponse(
    val success: Boolean,
    val declaration: CustomsDeclaration? = null,
    val totalDuties: Double,
    val totalTaxes: Double,
    val totalPayable: Double,
    val breakdown: List<CalculationBreakdown>,
    val warnings: List<String>
)
