package com.xiaotianqi.kuaipiao.cli

import java.util.Base64
import javax.crypto.KeyGenerator

object JwtKeyGenerator {

    fun generateSecretKey(): String {
        val keyGen = KeyGenerator.getInstance("HmacSHA256")
        keyGen.init(256)
        val secretKey = keyGen.generateKey()
        return Base64.getEncoder().encodeToString(secretKey.encoded)
    }
}
