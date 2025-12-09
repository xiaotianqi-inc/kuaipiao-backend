package com.xiaotianqi.kuaipiao.domain.dto.password

import java.time.Instant

data class PasswordResetData(
    val token: String,
    val userId: String,
    val expireAt: Instant,
    val createdAt: Instant = Instant.now()
)
