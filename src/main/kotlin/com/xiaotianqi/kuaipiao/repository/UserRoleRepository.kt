package com.xiaotianqi.kuaipiao.repository

import com.xiaotianqi.kuaipiao.domain.entity.rbac.UserRole
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRoleRepository : JpaRepository<UserRole, String> {
    fun findAllByUserId(userId: String): List<UserRole>
    fun findByUserIdAndRoleId(userId: String, roleId: String): UserRole?
    fun deleteByUserIdAndRoleId(userId: String, roleId: String)
}