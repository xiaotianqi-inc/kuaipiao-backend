package com.xiaotianqi.kuaipiao.repository

import com.xiaotianqi.kuaipiao.domain.entity.user.PasswordReset
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PasswordResetRepository : JpaRepository<PasswordReset, String> {
    fun findByToken(token: String): PasswordReset?
}