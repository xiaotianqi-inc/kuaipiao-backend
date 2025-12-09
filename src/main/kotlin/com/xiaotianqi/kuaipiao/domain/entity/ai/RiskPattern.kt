package com.xiaotianqi.kuaipiao.domain.entity.ai

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import java.time.Instant
import java.math.BigDecimal

@Entity
@Table(name = "compliance_risk_patterns")
data class RiskPattern(
    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    val id: String = "",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "analysis_id", nullable = false)
    val analysis: ComplianceRisk,

    @Column(name = "pattern_type", nullable = false)
    val patternType: String,

    @Lob
    @Column(nullable = false)
    val description: String,

    @Column(nullable = false)
    val severity: String,

    @Column(nullable = false)
    val occurrences: Int,

    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    val totalAmount: BigDecimal,

    @Column(name = "first_occurrence", nullable = false)
    val firstOccurrence: Instant,

    @Column(name = "last_occurrence", nullable = false)
    val lastOccurrence: Instant
)
