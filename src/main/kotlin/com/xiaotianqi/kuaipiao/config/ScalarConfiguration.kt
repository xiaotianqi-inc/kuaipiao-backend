package com.xiaotianqi.kuaipiao.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ScalarConfiguration {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Kuaipiao API")
                    .version("1.0.0")
                    .description("""
                        # Kuaipiao API Documentation
                            A modern, cross-platform API for the Kuaipiao system.
                        
                        ## Features
                            - OAuth2 and JWT authentication
                            - GraphQL support
                            - Messaging with Kafka and RabbitMQ
                            - Caching with Redis
                            - Integration with multiple services
                        
                        ## Security
                            The API requires authentication using a Bearer Token or API Key.
                        
                        ## Rate Limiting
                            - 100 requests per minute for public endpoints
                            - 1000 requests per minute for authenticated endpoints
                    """.trimIndent())
                    .contact(
                        Contact()
                            .name("Equipo Kuaipiao")
                            .email("api@kuaipiao.com")
                            .url("https://kuaipiao.com")
                    )
                    .license(
                        License()
                            .name("MIT License")
                            .url("https://opensource.org/licenses/MIT")
                    )
            )
            .servers(
                listOf(
                    Server()
                        .url("https://api.kuaipiao.com")
                        .description("Production Server"),
                    Server()
                        .url("https://staging.kuaipiao.com")
                        .description("Staging Server"),
                    Server()
                        .url("http://localhost:8080")
                        .description("Local Development Server")
                )
            )
            .components(
                Components()
                    .addSecuritySchemes(
                        "BearerAuth",
                        SecurityScheme()
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT")
                            .description("JWT Bearer Token")
                    )
                    .addSecuritySchemes(
                        "ApiKey",
                        SecurityScheme()
                            .type(SecurityScheme.Type.APIKEY)
                            .`in`(SecurityScheme.In.HEADER)
                            .name("X-API-Key")
                            .description("API Key for authentication")
                    )
                    .addSecuritySchemes(
                        "OAuth2",
                        SecurityScheme()
                            .type(SecurityScheme.Type.OAUTH2)
                            .description("OAuth2 Authorization Code Flow")
                            .flows(
                                io.swagger.v3.oas.models.security.OAuthFlows()
                                    .authorizationCode(
                                        io.swagger.v3.oas.models.security.OAuthFlow()
                                            .authorizationUrl("https://auth.kuaipiao.com/oauth2/authorize")
                                            .tokenUrl("https://auth.kuaipiao.com/oauth2/token")
                                            .scopes(
                                                io.swagger.v3.oas.models.security.Scopes()
                                                    .addString("read", "Read permission")
                                                    .addString("write", "Writing permission")
                                                    .addString("admin", "Administrator permission")
                                            )
                                    )
                            )
                    )
            )
            .addSecurityItem(
                SecurityRequirement()
                    .addList("BearerAuth")
            )
    }
}