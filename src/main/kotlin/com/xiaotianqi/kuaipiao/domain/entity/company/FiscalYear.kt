package com.xiaotianqi.kuaipiao.domain.entity.company

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import com.xiaotianqi.kuaipiao.enums.AuditStatus

@Entity
@Table(name = "fiscal_years")
data class FiscalYear(
    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    val id: String = "",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    val company: Company,

    @Column(nullable = false)
    val year: Int,

    @Column(name = "total_revenue", nullable = false)
    val totalRevenue: Double,

    @Column(name = "total_expenses", nullable = false)
    val totalExpenses: Double,

    @Column(name = "net_income", nullable = false)
    val netIncome: Double,

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status", nullable = false)
    val auditStatus: AuditStatus
)
