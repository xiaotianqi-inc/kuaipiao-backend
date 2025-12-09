package com.xiaotianqi.kuaipiao.controller

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import com.xiaotianqi.kuaipiao.domain.entity.company.Company
import com.xiaotianqi.kuaipiao.service.CompanyService

@RestController
@RequestMapping("/company")
class CompanyController(private val companyService: CompanyService) {

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('MANAGE', 'CREATE')")
    fun create(@Valid @RequestBody company: Company): ResponseEntity<Company> {
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.create(company))
    }

    @GetMapping("/find/id/{id}")
    @PreAuthorize("hasAuthority('VIEW')")
    fun getById(@PathVariable id: String): ResponseEntity<Company> {
        return ResponseEntity.ok(companyService.getById(id))
    }

    @GetMapping("/find/tax-id/{taxId}")
    @PreAuthorize("hasAuthority('VIEW')")
    fun getByTaxId(@PathVariable taxId: String): ResponseEntity<Company> {
        return ResponseEntity.ok(companyService.getByTaxId(taxId))
    }

    @GetMapping("/find/industry/{id}")
    @PreAuthorize("hasAuthority('VIEW')")
    fun getByIndustry(@PathVariable id: String): ResponseEntity<List<Company>> {
        return ResponseEntity.ok(companyService.getByIndustry(id))
    }

    @PutMapping("/update/{id}/industry")
    @PreAuthorize("hasAnyAuthority('MANAGE', 'UPDATE')")
    fun updateIndustry(
        @PathVariable id: String,
        @RequestBody request: UpdateIndustryRequest
    ): ResponseEntity<Map<String, String>> {
        companyService.updateIndustry(id, request.industry)
        return ResponseEntity.ok(mapOf("message" to "Company industry updated successfully"))
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGE', 'DELETE')")
    fun delete(@PathVariable id: String): ResponseEntity<Void> {
        companyService.delete(id)
        return ResponseEntity.noContent().build()
    }
}

data class UpdateIndustryRequest(val industry: String)