package com.xiaotianqi.kuaipiao.controller

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import com.xiaotianqi.kuaipiao.domain.entity.enterprise.Enterprise
import com.xiaotianqi.kuaipiao.enums.EntityStatus
import com.xiaotianqi.kuaipiao.enums.EnterprisePlan
import com.xiaotianqi.kuaipiao.service.EnterpriseService

@RestController
@RequestMapping("/enterprise")
class EnterpriseController(private val enterpriseService: EnterpriseService) {

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('MANAGE', 'CREATE')")
    fun create(@Valid @RequestBody enterprise: Enterprise): ResponseEntity<Enterprise> {
        return ResponseEntity.status(HttpStatus.CREATED).body(enterpriseService.create(enterprise))
    }

    @GetMapping("/find/{id}")
    @PreAuthorize("hasAuthority('VIEW')")
    fun getById(@PathVariable id: String): ResponseEntity<Enterprise> {
        return ResponseEntity.ok(enterpriseService.getById(id))
    }

    @GetMapping("/find/subdomain/{subdomain}")
    @PreAuthorize("hasAuthority('VIEW')")
    fun getBySubdomain(@PathVariable subdomain: String): ResponseEntity<Enterprise> {
        return ResponseEntity.ok(enterpriseService.getBySubdomain(subdomain))
    }

    @PatchMapping("/update/{id}/status")
    @PreAuthorize("hasAnyAuthority('MANAGE', 'UPDATE')")
    fun updateStatus(
        @PathVariable id: String,
        @RequestBody request: Map<String, String>
    ): ResponseEntity<Map<String, String>> {
        val status = EntityStatus.valueOf(request["status"] ?: return ResponseEntity.badRequest().build())
        enterpriseService.updateStatus(id, status)
        return ResponseEntity.ok(mapOf("message" to "Status updated successfully"))
    }

    @PatchMapping("/update/{id}/plan")
    @PreAuthorize("hasAnyAuthority('MANAGE', 'UPDATE')")
    fun updatePlan(
        @PathVariable id: String,
        @RequestBody request: Map<String, String>
    ): ResponseEntity<Map<String, String>> {
        val plan = EnterprisePlan.valueOf(request["plan"] ?: return ResponseEntity.badRequest().build())
        enterpriseService.updatePlan(id, plan)
        return ResponseEntity.ok(mapOf("message" to "Plan updated successfully"))
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGE', 'DELETE')")
    fun delete(@PathVariable id: String): ResponseEntity<Void> {
        enterpriseService.delete(id)
        return ResponseEntity.noContent().build()
    }
}