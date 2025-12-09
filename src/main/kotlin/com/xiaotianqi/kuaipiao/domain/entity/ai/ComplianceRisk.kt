package com.xiaotianqi.kuaipiao.domain.entity.ai

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import java.time.Instant

@Entity
@Table(name = "compliance_risk_analysis")
data class ComplianceRisk(
    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    val id: String = "",

    @Column(name = "company_id", nullable = false)
    val companyId: String,

    @Column(name = "period_start", nullable = false)
    val periodStart: Instant,

    @Column(name = "period_end", nullable = false)
    val periodEnd: Instant,

    @Column(name = "risk_score", nullable = false)
    val riskScore: Double,

    @Column(name = "audit_probability", nullable = false)
    val auditProbability: Double,

    @Column(name = "high_risk_transaction_count", nullable = false)
    val highRiskTransactionCount: Int,

    @Lob
    @Column(nullable = false)
    val recommendations: String,

    @Column(name = "next_review_date", nullable = false)
    val nextReviewDate: Instant,

    @Column(name = "analysis_date", nullable = false)
    val analysisDate: Instant,

    @Column(name = "created_at", nullable = false)
    val createdAt: Instant,

    @OneToMany(mappedBy = "analysis", fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    val riskPatterns: Set<RiskPattern> = emptySet()
)
