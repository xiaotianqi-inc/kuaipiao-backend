package com.xiaotianqi.kuaipiao.domain.entity.ai

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import com.xiaotianqi.kuaipiao.domain.entity.invoice.Invoice
import java.time.Instant

@Entity
@Table(name = "document_processing")
data class DocumentProcessing(
    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    val id: String = "",

    @Column(name = "user_id", nullable = false)
    val userId: String,

    @Column(name = "company_id", nullable = false)
    val companyId: String,

    @Column(name = "file_type", nullable = false)
    val fileType: String,

    @Column(name = "document_type", nullable = false)
    val documentType: String,

    @Column(name = "file_name", nullable = false)
    val fileName: String,

    @Column(name = "file_size", nullable = false)
    val fileSize: Long,

    @Column(nullable = false)
    val confidence: Double,

    @Column(name = "processing_time", nullable = false)
    val processingTime: Long,

    @Column(name = "ai_provider", nullable = false)
    val aiProvider: String,

    @Column(name = "extracted_fields", nullable = false)
    val extractedFields: Int,

    @Column(nullable = false)
    val status: String,

    @Lob
    @Column(name = "error_message")
    val errorMessage: String? = null,

    @Column(name = "created_at", nullable = false)
    val createdAt: Instant,

    @OneToOne(mappedBy = "documentProcessing", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    var invoiceData: Invoice? = null
)
