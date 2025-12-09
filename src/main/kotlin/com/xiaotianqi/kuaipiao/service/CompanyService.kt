package com.xiaotianqi.kuaipiao.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import com.xiaotianqi.kuaipiao.domain.entity.company.Company
import com.xiaotianqi.kuaipiao.exception.ResourceNotFoundException
import com.xiaotianqi.kuaipiao.repository.CompanyRepository
import java.time.Instant

@Service
@Transactional
class CompanyService(private val companyRepository: CompanyRepository) {

    fun create(company: Company): Company {
        return companyRepository.save(company.copy(createdAt = Instant.now()))
    }

    fun getById(id: String): Company {
        return companyRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Company not found with id: $id") }
    }

    fun getByTaxId(taxId: String): Company {
        return companyRepository.findByTaxId(taxId)
            ?: throw ResourceNotFoundException("Company not found with taxId: $taxId")
    }

    fun getByIndustry(industry: String): List<Company> {
        return companyRepository.findByIndustry(industry)
    }

    fun updateIndustry(id: String, industry: String): Company {
        val company = getById(id)
        return companyRepository.save(
            company.copy(industry = industry, updatedAt = Instant.now())
        )
    }

    fun delete(id: String) {
        if (!companyRepository.existsById(id)) {
            throw ResourceNotFoundException("Company not found with id: $id")
        }
        companyRepository.deleteById(id)
    }
}