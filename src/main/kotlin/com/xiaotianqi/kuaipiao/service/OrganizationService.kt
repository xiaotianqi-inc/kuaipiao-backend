package com.xiaotianqi.kuaipiao.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import com.xiaotianqi.kuaipiao.domain.entity.organization.Organization
import com.xiaotianqi.kuaipiao.enums.EntityStatus
import com.xiaotianqi.kuaipiao.exception.ResourceNotFoundException
import com.xiaotianqi.kuaipiao.repository.OrganizationRepository
import java.time.Instant

@Service
@Transactional
class OrganizationService(private val organizationRepository: OrganizationRepository) {

    fun create(organization: Organization): Organization {
        return organizationRepository.save(organization.copy(createdAt = Instant.now()))
    }

    fun getById(id: String): Organization {
        return organizationRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Organization not found with id: $id") }
    }

    fun getByCode(code: String): Organization {
        return organizationRepository.findByCode(code)
            ?: throw ResourceNotFoundException("Organization not found with code: $code")
    }

    fun updateStatus(id: String, status: EntityStatus): Organization {
        val organization = getById(id)
        return organizationRepository.save(
            organization.copy(status = status, updatedAt = Instant.now())
        )
    }

    fun delete(id: String) {
        if (!organizationRepository.existsById(id)) {
            throw ResourceNotFoundException("Organization not found with id: $id")
        }
        organizationRepository.deleteById(id)
    }
}