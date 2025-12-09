package com.xiaotianqi.kuaipiao

import com.xiaotianqi.kuaipiao.config.JwtProperties
import io.github.cdimascio.dotenv.dotenv
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties::class)
@EnableCaching
@EnableAsync
class KuaipiaoApplication

fun main(args: Array<String>) {

    val env = dotenv {
        ignoreIfMalformed = true
        ignoreIfMissing = false
    }

    env.entries().forEach { entry ->
        println("Loading ENV -> ${entry.key}")
        System.setProperty(entry.key, entry.value)
    }

	runApplication<KuaipiaoApplication>(*args)
}
