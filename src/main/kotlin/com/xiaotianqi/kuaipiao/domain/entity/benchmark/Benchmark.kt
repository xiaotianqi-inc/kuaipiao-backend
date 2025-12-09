package com.xiaotianqi.kuaipiao.domain.entity.benchmark

import jakarta.persistence.*
import com.xiaotianqi.kuaipiao.enums.RiskPattern

@Entity
@Table(name = "industry_benchmarks")
data class Benchmark(
    @Id
    @Column(name = "industry", updatable = false, nullable = false)
    val industry: String = "",

    @Column(name = "avg_transaction_size", nullable = false)
    val avgTransactionSize: Double,

    @Column(name = "typical_transaction_count", nullable = false)
    val typicalTransactionCount: Int,

    @Lob
    @Column(name = "common_risk_patterns", nullable = false)
    val commonRiskPatternsRaw: String,

    @Column(name = "tax_compliance_rate", nullable = false)
    val taxComplianceRate: Double = 0.95,

    @Column(name = "audit_probability", nullable = false)
    val auditProbability: Double = 0.15
) {
    @get:Transient
    val commonRiskPatterns: List<RiskPattern>
        get() = commonRiskPatternsRaw.split(",")
            .mapNotNull { pattern ->
                runCatching { RiskPattern.valueOf(pattern.trim()) }.getOrNull()
            }
}
