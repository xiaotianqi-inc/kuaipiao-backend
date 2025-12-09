package com.xiaotianqi.kuaipiao.repository

import com.xiaotianqi.kuaipiao.domain.entity.rbac.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<Role, String> {
    fun findByName(name: String): Role?
}