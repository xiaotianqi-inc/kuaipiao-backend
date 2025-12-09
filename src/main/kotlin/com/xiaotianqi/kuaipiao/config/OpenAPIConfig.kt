package com.xiaotianqi.kuaipiao.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.servers.Server
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenAPIConfig {

    @Bean
    fun openAPI(
        @Value("\${server.port}") port: Int
    ): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("KuaiPiao API by xiaotianqi.com")
                    .version("1.0.0")
                    .description("AI-powered API for business management")
                    .contact(
                        Contact()
                            .name("KuaiPiao Team")
                            .url("https://xiaotianqi.com/kuaipiao")
                            .email("support@xiaotianqi.com")
                    )
                    .license(
                        License()
                            .name("MIT License")
                            .url("https://opensource.org/licenses/MIT")
                    )
            )
            .servers(
                listOf(
                    Server().url("http://localhost:$port").description("Development"),
                    Server().url("https://api.xiaotianqi.com").description("Production")
                )
            )
            .addSecurityItem(SecurityRequirement().addList("Bearer Authentication"))
            .components(
                Components()
                    .addSecuritySchemes(
                        "Bearer Authentication",
                        SecurityScheme()
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT")
                    )
            )
    }
}