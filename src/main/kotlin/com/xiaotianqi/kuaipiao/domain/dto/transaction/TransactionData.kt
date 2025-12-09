package com.xiaotianqi.kuaipiao.domain.dto.transaction

import com.xiaotianqi.kuaipiao.enums.RiskLevel
import com.xiaotianqi.kuaipiao.enums.TransactionType
import java.time.Instant

data class TransactionData(
    val id: String,
    val date: Instant,
    val amount: Double,
    val currency: String,
    val type: TransactionType,
    val counterparty: String,
    val description: String,
    val metadata: Map<String, String> = emptyMap()
)

data class SuspiciousTransaction(
    val transactionId: String,
    val riskLevel: RiskLevel,
    val reasons: List<String>,
    val suggestedActions: List<String>
)
