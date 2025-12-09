package com.xiaotianqi.kuaipiao.domain.dto.invoice

import com.xiaotianqi.kuaipiao.domain.dto.accounting.AccountSuggestion
import com.xiaotianqi.kuaipiao.domain.dto.accounting.AccountingPattern
import com.xiaotianqi.kuaipiao.domain.dto.customer.CustomerData
import com.xiaotianqi.kuaipiao.domain.dto.document.DocumentData
import com.xiaotianqi.kuaipiao.domain.dto.provider.ProviderData
import com.xiaotianqi.kuaipiao.enums.*
import java.time.Instant

data class InvoiceData(
    val id: String,
    val providerInfo: ProviderData,
    val customerInfo: CustomerData,
    val number: String,
    val date: Instant,
    val dueDate: Instant,
    val items: List<InvoiceItemData> = emptyList(),
    val subtotal: String,
    val taxes: List<InvoiceTax> = emptyList(),
    val tax: String,
    val total: String,
    val status: OperationStatus = OperationStatus.ISSUED,
    val paymentStatus: PaymentStatus = PaymentStatus.PENDING,
    val currency: String,
    val notes: String? = null,
    val metadata: Map<String, String> = emptyMap(),
    val createdAt: Instant = Instant.now(),
)

data class InvoiceItemData(
    val id: String,
    val code: String,
    val productId: String,
    val description: String,
    val quantity: String,
    val unitPrice: String,
    val discount: String = "0",
    val tax: String? = "0.0",
    val taxAmount: String? = "0.0",
    val subtotal: String? = "0.0",
    val total: String = "0.0",
    val unitOfMeasure: String? = null,
    val itemType: ItemType? = null
)

data class InvoiceTax(
    val type: TaxType,
    val rate: Double,
    val amount: Double
)

data class InvoiceBusiness(
    val id: String,
    val name: String,
    val country: String,
    val region: String? = null,
    val city: String? = null,
    val address: String? = null,
    val taxId: String? = null
)

data class ElectronicInvoiceData(
    val invoiceId: InvoiceData,
    val accessKey: String,
    val xmlSigned: String,
    val authorizationDate: Instant? = null,
    val authorizationNumber: String? = null,
    val status: InvoiceStatus
)

data class InvoiceExtractionRequest(
    val imageData: ByteArray,
    val documentType: DocumentType,
    val historicalPatterns: List<AccountingPattern> = emptyList()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        other as InvoiceExtractionRequest
        if (!imageData.contentEquals(other.imageData)) return false
        if (documentType != other.documentType) return false
        if (historicalPatterns != other.historicalPatterns) return false
        return true
    }

    override fun hashCode(): Int {
        var result = imageData.contentHashCode()
        result = 31 * result + documentType.hashCode()
        result = 31 * result + historicalPatterns.hashCode()
        return result
    }
}

data class InvoiceExtractionResponse(
    val vendor: String,
    val invoiceNumber: String,
    val date: String,
    val totalAmount: Double,
    val currency: String,
    val lineItems: List<LineItem>,
    val suggestedAccounts: Map<String, AccountSuggestion>,
    val confidence: Double
)

data class LineItem(
    val description: String,
    val quantity: Double,
    val unitPrice: Double,
    val totalPrice: Double,
    val taxRate: Double? = null
)

data class InvoiceProcessingResult(
    val documentId: String,
    val invoiceId: String,
    val supplierName: String,
    val totalAmount: Double,
    val currency: String,
    val issueDate: Instant,
    val taxAmount: Double,
    val extractedData: InvoiceData,
    val confidence: Double,
    val validationErrors: List<String>,
    val suggestions: List<String>,
    val processingTime: Long,
    val aiProvider: AiProvider,
    val status: OperationStatus
)

data class InvoiceResult(
    val invoiceNumber: String,
    val date: String,
    val total: Double,
    val currency: String,
    val items: List<String>
)

fun InvoiceData.toDocumentData(): DocumentData {
    return DocumentData(
        documentId = this.id,
        invoiceNumber = this.number,
        issueDate = this.date.toString(),
        currency = this.currency,
        emitterRuc = this.providerInfo.documentNumber,
        emitterName = this.providerInfo.name,
        receiverRuc = this.customerInfo.documentNumber,
        receiverName = this.customerInfo.businessName ?: this.customerInfo.firstName,
        subtotal = this.subtotal.toDoubleOrNull(),
        taxAmount = this.tax.toDoubleOrNull(),
        total = this.total.toDoubleOrNull(),
        items = this.items,
        country = this.customerInfo.address?.country,
        rawContent = null
    )
}

object InvoiceCountryRules {
    fun getTaxRate(country: String): Double = when (country.lowercase()) {
        "ecuador" -> 15.0
        "colombia" -> 19.0
        "chile" -> 19.0
        "peru" -> 18.0
        "estados unidos", "usa" -> 0.0
        else -> 10.0
    }
    fun getTaxType(country: String): String = when (country.lowercase()) {
        "ecuador" -> "IVA"
        "colombia" -> "IVA"
        "chile" -> "IVA"
        "peru" -> "IGV"
        "estados unidos", "usa" -> "SALES_TAX"
        else -> "VAT"
    }
}
