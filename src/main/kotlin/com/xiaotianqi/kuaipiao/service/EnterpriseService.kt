package com.xiaotianqi.kuaipiao.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import com.xiaotianqi.kuaipiao.domain.entity.enterprise.Enterprise
import com.xiaotianqi.kuaipiao.enums.EntityStatus
import com.xiaotianqi.kuaipiao.enums.EnterprisePlan
import com.xiaotianqi.kuaipiao.exception.ResourceNotFoundException
import com.xiaotianqi.kuaipiao.repository.EnterpriseRepository
import java.time.Instant

@Service
@Transactional
class EnterpriseService(private val enterpriseRepository: EnterpriseRepository) {

    fun create(enterprise: Enterprise): Enterprise {
        if (enterpriseRepository.existsBySubdomain(enterprise.subdomain)) {
            throw IllegalArgumentException("Subdomain already exists")
        }
        enterprise.domain?.let {
            if (enterpriseRepository.existsByDomain(it)) {
                throw IllegalArgumentException("Domain already exists")
            }
        }
        return enterpriseRepository.save(enterprise.copy(createdAt = Instant.now()))
    }

    fun getById(id: String): Enterprise {
        return enterpriseRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Enterprise not found with id: $id") }
    }

    fun getBySubdomain(subdomain: String): Enterprise {
        return enterpriseRepository.findBySubdomain(subdomain)
            ?: throw ResourceNotFoundException("Enterprise not found with subdomain: $subdomain")
    }

    fun updateStatus(id: String, status: EntityStatus): Enterprise {
        val enterprise = getById(id)
        return enterpriseRepository.save(
            enterprise.copy(status = status, updatedAt = Instant.now())
        )
    }

    fun updatePlan(id: String, plan: EnterprisePlan): Enterprise {
        val enterprise = getById(id)
        return enterpriseRepository.save(
            enterprise.copy(plan = plan, updatedAt = Instant.now())
        )
    }

    fun delete(id: String) {
        if (!enterpriseRepository.existsById(id)) {
            throw ResourceNotFoundException("Enterprise not found with id: $id")
        }
        enterpriseRepository.deleteById(id)
    }
}