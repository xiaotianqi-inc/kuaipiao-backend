package com.xiaotianqi.kuaipiao.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.xiaotianqi.kuaipiao.domain.entity.enterprise.Enterprise

@Repository
interface EnterpriseRepository : JpaRepository<Enterprise, String> {
    fun findBySubdomain(subdomain: String): Enterprise?
    fun existsBySubdomain(subdomain: String): Boolean
    fun existsByDomain(domain: String): Boolean
}