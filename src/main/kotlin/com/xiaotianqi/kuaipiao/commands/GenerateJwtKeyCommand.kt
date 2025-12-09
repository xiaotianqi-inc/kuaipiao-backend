package com.xiaotianqi.kuaipiao.commands

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.util.Base64
import javax.crypto.KeyGenerator

@Component
class GenerateJwtKeyCommand : CommandLineRunner {

    override fun run(vararg args: String) {

        if (args.isNotEmpty() && args[0] == "jwt:generate") {

            val keyGen = KeyGenerator.getInstance("HmacSHA256")
            keyGen.init(256)
            val secretKey = keyGen.generateKey()

            val encoded = Base64.getEncoder().encodeToString(secretKey.encoded)

            println("\n==============================================")
            println(" 🔐 JWT SECRET GENERATED")
            println("==============================================")
            println("Add this to your .env or environment variables:\n")
            println("export JWT_SECRET=$encoded")
            println("\n==============================================\n")

            kotlin.system.exitProcess(0)
        }
    }
}
