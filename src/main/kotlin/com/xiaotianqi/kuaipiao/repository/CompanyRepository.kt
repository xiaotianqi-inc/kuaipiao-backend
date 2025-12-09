package com.xiaotianqi.kuaipiao.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import com.xiaotianqi.kuaipiao.domain.entity.company.Company

@Repository
interface CompanyRepository : JpaRepository<Company, String> {
    fun findByTaxId(taxId: String): Company?

    @Query("SELECT c FROM Company c WHERE c.industry = :industry")
    fun findByIndustry(industry: String): List<Company>
}