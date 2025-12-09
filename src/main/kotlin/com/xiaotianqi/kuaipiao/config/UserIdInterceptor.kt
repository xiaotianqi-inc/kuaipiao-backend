package com.xiaotianqi.kuaipiao.config

import org.springframework.context.annotation.Configuration
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import com.xiaotianqi.kuaipiao.service.JwtService

@Component
class UserIdInterceptor(
    private val jwtService: JwtService
) : HandlerInterceptor {

    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any
    ): Boolean {
        val auth = SecurityContextHolder.getContext().authentication

        if (auth != null && auth.isAuthenticated) {
            val authHeader = request.getHeader("Authorization")
            if (authHeader?.startsWith("Bearer ") == true) {
                val token = authHeader.substring(7)
                val userId = jwtService.extractUserId(token)
                request.setAttribute("userId", userId)
            }
        }

        return true
    }
}