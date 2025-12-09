package com.xiaotianqi.kuaipiao.domain.dto.email

import java.time.Instant

data class EmailVerificationData(
    val token: String,
    val userId: String,
    val expireAt: Instant,
    val createdAt: Instant = Instant.now()
)
