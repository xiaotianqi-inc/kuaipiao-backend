package com.xiaotianqi.kuaipiao.domain.entity.invoice

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import com.xiaotianqi.kuaipiao.domain.entity.ai.DocumentProcessing
import java.math.BigDecimal

@Entity
@Table(name = "invoice_processing_data")
data class Invoice(
    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    val id: String = "",

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "processing_id", nullable = false)
    val documentProcessing: DocumentProcessing,

    @Column(name = "invoice_number", nullable = false)
    val invoiceNumber: String,

    @Column(name = "issue_date", nullable = false)
    val issueDate: String,

    @Column(name = "emitter_ruc", nullable = false)
    val emitterRuc: String,

    @Column(name = "receiver_ruc", nullable = false)
    val receiverRuc: String,

    @Column(nullable = false, precision = 10, scale = 2)
    val subtotal: BigDecimal,

    @Column(nullable = false, precision = 10, scale = 2)
    val total: BigDecimal,

    @Column(nullable = false)
    val currency: String,

    @Column(name = "items_count", nullable = false)
    val itemsCount: Int,

    @Column(name = "taxes_amount", nullable = false, precision = 10, scale = 2)
    val taxesAmount: BigDecimal,

    @Lob
    @Column(name = "raw_text")
    val rawText: String? = null
)
