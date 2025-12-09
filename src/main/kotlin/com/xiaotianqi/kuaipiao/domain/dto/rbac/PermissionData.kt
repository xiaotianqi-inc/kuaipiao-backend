package com.xiaotianqi.kuaipiao.domain.dto.rbac

data class PermissionDto(
    val id: String,
    val code: String,
    val name: String,
    val description: String?
)

data class CreatePermissionRequest(
    val code: String,
    val name: String,
    val description: String?
)
