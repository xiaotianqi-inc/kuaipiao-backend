package com.xiaotianqi.kuaipiao.service

import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TokenGenerator {
    fun generate(bytes: Int = 32): Pair<String, String> {
        val token = UUID.randomUUID().toString()
        return Pair(token, token)
    }
}