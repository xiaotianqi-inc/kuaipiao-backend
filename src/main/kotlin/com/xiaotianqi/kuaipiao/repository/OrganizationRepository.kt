package com.xiaotianqi.kuaipiao.repository

import com.xiaotianqi.kuaipiao.domain.entity.organization.Organization
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrganizationRepository : JpaRepository<Organization, String> {
    fun findByCode(code: String): Organization?
}