package com.xiaotianqi.kuaipiao.controller

import com.xiaotianqi.kuaipiao.domain.dto.rbac.CreatePermissionRequest
import com.xiaotianqi.kuaipiao.domain.dto.rbac.CreateRoleRequest
import com.xiaotianqi.kuaipiao.domain.dto.rbac.RoleDto
import com.xiaotianqi.kuaipiao.domain.entity.rbac.Permission
import com.xiaotianqi.kuaipiao.domain.entity.rbac.Role
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import com.xiaotianqi.kuaipiao.service.RBACService

@RestController
@RequestMapping("/rbac")
@PreAuthorize("hasRole('SUPER_ADMIN')")
class RBACController(private val rbacService: RBACService) {

    @PostMapping("/roles")
    fun createRole(@RequestBody request: CreateRoleRequest): ResponseEntity<RoleDto> {
        val role = rbacService.createRole(
            request.name,
            request.description,
            request.permissionIds.toList()
        )
        return ResponseEntity.status(HttpStatus.CREATED).body(RoleDto.fromEntity(role))
    }

    @PostMapping("/users/{userId}/roles/{roleId}")
    fun assignRole(
        @PathVariable userId: String,
        @PathVariable roleId: String,
        @RequestAttribute("userId") adminId: String
    ): ResponseEntity<Map<String, String>> {
        rbacService.assignRoleToUser(userId, roleId, adminId)
        return ResponseEntity.ok(mapOf("message" to "Role assigned successfully"))
    }

    @DeleteMapping("/users/{userId}/roles/{roleId}")
    fun removeRole(
        @PathVariable userId: String,
        @PathVariable roleId: String
    ): ResponseEntity<Map<String, String>> {
        rbacService.removeRoleFromUser(userId, roleId)
        return ResponseEntity.ok(mapOf("message" to "Role removed successfully"))
    }

    @GetMapping("/users/{userId}/roles")
    fun getUserRoles(@PathVariable userId: String): ResponseEntity<List<Role>> {
        return ResponseEntity.ok(rbacService.getUserRoles(userId))
    }

    @GetMapping("/users/{userId}/permissions")
    fun getUserPermissions(@PathVariable userId: String): ResponseEntity<Set<String>> {
        return ResponseEntity.ok(rbacService.getUserPermissions(userId))
    }

    @PostMapping("/permissions")
    fun createPermission(@RequestBody request: CreatePermissionRequest): ResponseEntity<Permission> {
        val permission = rbacService.createPermission(request.code, request.name, request.description)
        return ResponseEntity.status(HttpStatus.CREATED).body(permission)
    }
}
