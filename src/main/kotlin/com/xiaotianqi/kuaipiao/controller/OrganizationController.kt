package com.xiaotianqi.kuaipiao.controller

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import com.xiaotianqi.kuaipiao.domain.entity.organization.Organization
import com.xiaotianqi.kuaipiao.enums.EntityStatus
import com.xiaotianqi.kuaipiao.service.OrganizationService

@RestController
@RequestMapping("/org")
class OrganizationController(private val organizationService: OrganizationService) {

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('MANAGE', 'CREATE')")
    fun create(@Valid @RequestBody organization: Organization): ResponseEntity<Organization> {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationService.create(organization))
    }

    @GetMapping("/find/id/{id}")
    @PreAuthorize("hasAuthority('VIEW')")
    fun getById(@PathVariable id: String): ResponseEntity<Organization> {
        return ResponseEntity.ok(organizationService.getById(id))
    }

    @GetMapping("/find/code/{code}")
    @PreAuthorize("hasAuthority('VIEW')")
    fun getByCode(@PathVariable code: String): ResponseEntity<Organization> {
        return ResponseEntity.ok(organizationService.getByCode(code))
    }

    @PutMapping("/update/{id}/status")
    @PreAuthorize("hasAnyAuthority('MANAGE', 'UPDATE')")
    fun updateStatus(
        @PathVariable id: String,
        @RequestBody request: UpdateStatusRequest
    ): ResponseEntity<Map<String, String>> {
        organizationService.updateStatus(id, request.status)
        return ResponseEntity.ok(mapOf("message" to "Organization status updated successfully"))
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGE', 'DELETE')")
    fun delete(@PathVariable id: String): ResponseEntity<Void> {
        organizationService.delete(id)
        return ResponseEntity.noContent().build()
    }
}

data class UpdateStatusRequest(val status: EntityStatus)