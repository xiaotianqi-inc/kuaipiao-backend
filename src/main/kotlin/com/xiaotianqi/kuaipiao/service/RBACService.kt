package com.xiaotianqi.kuaipiao.service

import com.xiaotianqi.kuaipiao.domain.entity.rbac.Permission
import com.xiaotianqi.kuaipiao.domain.entity.rbac.Role
import com.xiaotianqi.kuaipiao.domain.entity.rbac.UserRole
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import com.xiaotianqi.kuaipiao.exception.ResourceNotFoundException
import com.xiaotianqi.kuaipiao.repository.*

@Service
@Transactional
class RBACService(
    private val roleRepository: RoleRepository,
    private val permissionRepository: PermissionRepository,
    private val userRoleRepository: UserRoleRepository,
    private val userRepository: UserRepository
) {

    fun createRole(name: String, description: String?, permissionIds: List<String>): Role {

        val permissionsSet = permissionIds.map { permissionCode ->
            Permission(
                code = permissionCode,
                name = "Default name for $permissionCode",
                description = null
            )
        }.toSet()

        val role = Role(
            id = java.util.UUID.randomUUID().toString(),
            name = name,
            description = description,
            permissions = permissionsSet
        )
        return roleRepository.save(role)
    }

    fun assignRoleToUser(userId: String, roleId: String, assignedBy: String?) {

        val user = userRepository.findById(userId)
            .orElseThrow { ResourceNotFoundException("User not found") }

        val role = roleRepository.findById(roleId)
            .orElseThrow { ResourceNotFoundException("Role not found") }

        if (userRoleRepository.findByUserIdAndRoleId(userId, roleId) != null) {
            throw IllegalArgumentException("User already has this role")
        }

        val userRole = UserRole(
            user = user,
            role = role
        )

        userRoleRepository.save(userRole)
    }

    fun removeRoleFromUser(userId: String, roleId: String) {
        userRoleRepository.deleteByUserIdAndRoleId(userId, roleId)
    }

    fun getUserRoles(userId: String): List<Role> {
        val userRoles = userRoleRepository.findAllByUserId(userId)
        val roleIds = userRoles.map { it.role.id }
        return roleRepository.findAllById(roleIds)
    }

    fun getUserPermissions(userId: String): Set<String> {
        val roles = getUserRoles(userId)

        return roles.flatMap { it.permissions }
                    .map { it.code }
                    .toSet()
    }

    fun createPermission(code: String, name: String, description: String?): Permission {
        val permission = Permission(
            id = java.util.UUID.randomUUID().toString(),
            code = code,
            name = name,
            description = description
        )
        return permissionRepository.save(permission)
    }

    fun hasPermission(userId: String, requiredPermission: String): Boolean {
        val userPermissions = getUserPermissions(userId)
        return userPermissions.contains(requiredPermission)
    }

    fun hasAnyPermission(userId: String, requiredPermissions: List<String>): Boolean {
        val userPermissions = getUserPermissions(userId)
        return requiredPermissions.any { it in userPermissions }
    }
}