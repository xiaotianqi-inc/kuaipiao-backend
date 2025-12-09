package com.xiaotianqi.kuaipiao.domain.dto.customer

import com.xiaotianqi.kuaipiao.domain.dto.address.AddressData
import com.xiaotianqi.kuaipiao.enums.BuyerDocumentType
import com.xiaotianqi.kuaipiao.enums.CustomerType

data class CustomerData(
    val id: String,
    val firstName: String? = null,
    val lastName: String? = null,
    val businessName: String? = null,
    val customerType: CustomerType = CustomerType.INDIVIDUAL,
    val documentType: BuyerDocumentType = BuyerDocumentType.PASSPORT,
    val documentNumber: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val address: AddressData? = null,
    val dateOfBirth: String? = null,
    val issueDate: String? = null,
    val expirationDate: String? = null,
    val rawText: String? = null
)