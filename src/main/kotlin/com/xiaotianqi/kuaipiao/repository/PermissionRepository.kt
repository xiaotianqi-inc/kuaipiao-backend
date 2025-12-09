package com.xiaotianqi.kuaipiao.repository

import com.xiaotianqi.kuaipiao.domain.entity.rbac.Permission
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PermissionRepository : JpaRepository<Permission, String> {
    fun findByCode(code: String): Permission?
    fun findAllByIdIn(ids: List<String>): List<Permission>
}