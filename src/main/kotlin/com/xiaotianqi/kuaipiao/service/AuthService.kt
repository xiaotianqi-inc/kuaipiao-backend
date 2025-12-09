package com.xiaotianqi.kuaipiao.service

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import com.xiaotianqi.kuaipiao.domain.dto.auth.*
import com.xiaotianqi.kuaipiao.domain.entity.user.EmailVerification
import com.xiaotianqi.kuaipiao.domain.entity.user.PasswordReset
import com.xiaotianqi.kuaipiao.domain.entity.user.User
import com.xiaotianqi.kuaipiao.repository.*
import com.xiaotianqi.kuaipiao.exception.ResourceNotFoundException
import java.time.Instant
import java.util.*

@Service
@Transactional
class AuthService(
    private val userRepository: UserRepository,
    private val emailVerificationRepository: EmailVerificationRepository,
    private val passwordResetRepository: PasswordResetRepository,
    private val enterpriseRepository: EnterpriseRepository,
    private val passwordEncoder: PasswordEncoder,
    private val emailService: EmailService,
    private val tokenGenerator: TokenGenerator
) {

    fun register(request: RegistrationRequest): User {
        if (userRepository.existsByEmail(request.email)) {
            throw IllegalArgumentException("Email already registered")
        }

        val enterprise = request.enterpriseId?.let { id ->
            enterpriseRepository.findById(id).orElseThrow {
                ResourceNotFoundException("Enterprise not found with ID: $id")
            }
        }

        val user = User(
            email = request.email,
            username = request.email.substringBefore("@"),
            passwordHash = passwordEncoder.encode(request.password)!!,
            firstName = request.firstName,
            lastName = request.lastName,
            enterprise = enterprise,
            emailVerified = false
        )

        val savedUser = userRepository.save(user)
        sendVerificationEmail(savedUser)
        return savedUser
    }

    fun verifyEmail(token: String) {
        val verification = emailVerificationRepository.findByToken(token)
            ?: throw ResourceNotFoundException("Invalid or expired verification token")

        if (verification.expiresAt.isBefore(Instant.now())) {
            throw IllegalArgumentException("Verification token has expired")
        }

        val user = userRepository.findById(verification.user.id)
            .orElseThrow { ResourceNotFoundException("User not found") }

        userRepository.save(user.copy(emailVerified = true, updatedAt = Instant.now()))
        emailVerificationRepository.deleteByUserId(user.id)
    }

    fun sendPasswordResetEmail(email: String) {
        val user = userRepository.findByEmail(email)
            ?: throw ResourceNotFoundException("User not found")

        val (token, _) = tokenGenerator.generate()
        val now = Instant.now()
        val expiryTime = now.plusSeconds(3600)

        val passwordReset = PasswordReset(
            token = token,
            user = user,
            createdAt = now,
            expiresAt = expiryTime
        )

        passwordResetRepository.save(passwordReset)
        emailService.sendPasswordResetEmail(user.email, token)
    }

    fun resetPassword(token: String, newPassword: String) {
        val reset = passwordResetRepository.findByToken(token)
            ?: throw ResourceNotFoundException("Invalid or expired reset token")

        if (reset.expiresAt.isBefore(Instant.now())) {
            throw IllegalArgumentException("Reset token has expired")
        }

        val user = userRepository.findById(reset.user.id)
            .orElseThrow { ResourceNotFoundException("User not found") }

        userRepository.save(
            user.copy(
                passwordHash = passwordEncoder.encode(newPassword)!!,
                updatedAt = Instant.now()
            )
        )

        passwordResetRepository.delete(reset)
    }

    fun findByEmail(email: String): User {
        return userRepository.findByEmail(email)
            ?: throw ResourceNotFoundException("User not found")
    }

    private fun sendVerificationEmail(user: User) {
        val (token, _) = tokenGenerator.generate()
        val now = Instant.now()
        val expiryTime = now.plusSeconds(3600)

        val verification = EmailVerification(
            token = token,
            user = user,
            createdAt = now,
            expiresAt = expiryTime
        )

        emailVerificationRepository.save(verification)
        emailService.sendVerificationEmail(user.email, token)
    }
}