package com.xiaotianqi.kuaipiao.controller

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*
import com.xiaotianqi.kuaipiao.domain.dto.auth.*
import com.xiaotianqi.kuaipiao.domain.dto.password.PasswordResetRequest
import com.xiaotianqi.kuaipiao.service.JwtService
import com.xiaotianqi.kuaipiao.service.AuthService

@RestController
@RequestMapping("/oauth")
class AuthController(
    private val authService: AuthService,
    private val authenticationManager: AuthenticationManager,
    private val jwtService: JwtService
) {

    @PostMapping("/sign-up")
    fun register(@Valid @RequestBody request: RegistrationRequest): ResponseEntity<MessageResponse> {
        authService.register(request)
        return ResponseEntity.ok(MessageResponse("Verification email sent"))
    }

    @PostMapping("/sign-in")
    fun login(@Valid @RequestBody request: LoginRequest): ResponseEntity<AuthResponse> {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(request.email, request.password)
        )

        val user = authService.findByEmail(request.email)
        val roleNames = user.roles.map { it.name }
        val token = jwtService.generateToken(
            userId = user.id,
            email = user.email,
            roles = roleNames
        )
        val userResponse = UserResponse.from(user)

        return ResponseEntity.ok(AuthResponse(token, userResponse))
    }

    @GetMapping("/verify-email")
    fun verifyEmail(@RequestParam token: String): ResponseEntity<MessageResponse> {
        authService.verifyEmail(token)
        return ResponseEntity.ok(MessageResponse("Email verified successfully"))
    }

    @GetMapping("/password-forgotten")
    fun forgotPassword(@RequestParam email: String): ResponseEntity<MessageResponse> {
        authService.sendPasswordResetEmail(email)
        return ResponseEntity.ok(MessageResponse("Password reset email sent"))
    }

    @PostMapping("/reset-password")
    fun resetPassword(
        @RequestParam token: String,
        @Valid @RequestBody request: PasswordResetRequest
    ): ResponseEntity<MessageResponse> {
        authService.resetPassword(token, request.password)
        return ResponseEntity.ok(MessageResponse("Password reset successfully"))
    }

    @PostMapping("/logout")
    fun logout(): ResponseEntity<MessageResponse> {
        return ResponseEntity.ok(MessageResponse("Logout successful"))
    }
}