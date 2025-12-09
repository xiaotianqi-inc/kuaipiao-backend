package com.xiaotianqi.kuaipiao.repository

import com.xiaotianqi.kuaipiao.domain.entity.user.EmailVerification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EmailVerificationRepository : JpaRepository<EmailVerification, String> {
    fun findByToken(token: String): EmailVerification?
    fun deleteByUserId(userId: String)
}