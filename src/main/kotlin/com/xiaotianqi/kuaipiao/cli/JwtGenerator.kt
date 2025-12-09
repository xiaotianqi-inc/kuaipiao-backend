package com.xiaotianqi.kuaipiao.cli

object JwtGenerator {
    @JvmStatic
    fun main(args: Array<String>) {

        println("🔐 Generando JWT...")

        val key = io.jsonwebtoken.security.Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256)
        val encoded = java.util.Base64.getEncoder().encodeToString(key.encoded)

        println("\n======================================")
        println(" JWT SECRET GENERATED")
        println("======================================")
        println("export JWT_SECRET=$encoded")
        println("======================================\n")
    }
}
