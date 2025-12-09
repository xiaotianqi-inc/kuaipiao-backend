package com.xiaotianqi.kuaipiao.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "jwt")
class JwtProperties {
    lateinit var secret: String
    var issuer: String = "kuaipiao"
    var audience: String = "kuaipiao-api"
    var expiration: Long = 86400000
}
