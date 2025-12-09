package com.xiaotianqi.kuaipiao.domain.dto.rbac

import com.xiaotianqi.kuaipiao.domain.entity.rbac.Role

data class RoleDto(
    val id: String,
    val name: String,
    val description: String?,
    val permissionIds: Set<String> = emptySet()
){
    companion object {
        fun fromEntity(roleEntity: Role): RoleDto {
            return RoleDto(
                id = roleEntity.id,
                name = roleEntity.name,
                description = roleEntity.description,
                permissionIds = roleEntity.permissions.map { it.id }.toSet()
            )
        }
    }
}

data class CreateRoleRequest(
    val name: String,
    val description: String?,
    val permissionIds: Set<String>
)