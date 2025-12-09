package com.xiaotianqi.kuaipiao.controller

import jakarta.validation.Valid
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import com.xiaotianqi.kuaipiao.domain.entity.user.User
import com.xiaotianqi.kuaipiao.domain.dto.auth.AdminRegisterRequest
import com.xiaotianqi.kuaipiao.domain.dto.auth.AdminResponse
import com.xiaotianqi.kuaipiao.domain.dto.auth.UpdateStatusRequest
import com.xiaotianqi.kuaipiao.domain.dto.auth.UserDetailDto
import com.xiaotianqi.kuaipiao.domain.dto.auth.UsersResponse
import com.xiaotianqi.kuaipiao.exception.ResourceNotFoundException
import com.xiaotianqi.kuaipiao.repository.UserRepository
import com.xiaotianqi.kuaipiao.repository.RoleRepository
import com.xiaotianqi.kuaipiao.service.JwtService
import java.time.Instant

@RestController
@RequestMapping("/oauth/admin")
@PreAuthorize("hasRole('SUPER_ADMIN')")
class AdminController(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService
) {

    @PostMapping("/sign-up")
    fun registerAdmin(@Valid @RequestBody request: AdminRegisterRequest): ResponseEntity<AdminResponse> {
        val allowedDomain = "xiaotianqi.com"
        val emailDomain = request.email.substringAfter("@").lowercase()

        if (emailDomain != allowedDomain) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(AdminResponse(error = "Invalid email domain"))
        }

        if (userRepository.existsByEmail(request.email)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(AdminResponse(error = "Email already registered"))
        }

        val superAdminRole = roleRepository.findByName("SUPER_ADMIN")
            ?: throw ResourceNotFoundException("SUPER_ADMIN role not found")

        val user = User(
            email = request.email,
            username = request.username ?: request.email.substringBefore("@"),
            passwordHash = passwordEncoder.encode(request.password)!!,
            firstName = request.firstName,
            lastName = request.lastName,
            emailVerified = true,
            enterprise = null,
            roles = setOf(superAdminRole)
        )

        val savedUser = userRepository.save(user)

        val token = jwtService.generateToken(
            userId = savedUser.id,
            email = savedUser.email,
            roles = listOf("SUPER_ADMIN")
        )

        return ResponseEntity.status(HttpStatus.CREATED).body(
            AdminResponse(
                message = "Super admin registered successfully",
                token = token,
                user = UserDetailDto.from(savedUser)
            )
        )
    }

    @GetMapping("/profile")
    fun getProfile(@RequestAttribute("userId") userId: String): ResponseEntity<UserDetailDto> {
        val user = userRepository.findById(userId)
            .orElseThrow { ResourceNotFoundException("User not found") }
        return ResponseEntity.ok(UserDetailDto.from(user))
    }

    @GetMapping("/users")
    fun listUsers(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "50") limit: Int
    ): ResponseEntity<UsersResponse> {
        val users = userRepository.findAll(PageRequest.of(page, limit))
        return ResponseEntity.ok(
            UsersResponse(
                users = users.content.map { UserDetailDto.from(it) },
                page = page,
                limit = limit,
                total = users.totalElements
            )
        )
    }

    @GetMapping("/users/{id}")
    fun getUserById(@PathVariable id: String): ResponseEntity<UserDetailDto> {
        val user = userRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("User not found") }
        return ResponseEntity.ok(UserDetailDto.from(user))
    }

    @PutMapping("/users/{id}/status")
    fun updateUserStatus(
        @PathVariable id: String,
        @RequestBody request: UpdateStatusRequest
    ): ResponseEntity<Map<String, Any>> {
        val user = userRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("User not found") }

        userRepository.save(user.copy(isActive = request.isActive, updatedAt = Instant.now()))

        return ResponseEntity.ok(mapOf(
            "message" to "User status updated",
            "userId" to id,
            "isActive" to request.isActive
        ))
    }

    @DeleteMapping("/users/{id}")
    fun deleteUser(
        @PathVariable id: String,
        @RequestAttribute("userId") adminId: String
    ): ResponseEntity<Void> {
        if (adminId == id) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        }

        if (!userRepository.existsById(id)) {
            throw ResourceNotFoundException("User not found")
        }

        userRepository.deleteById(id)
        return ResponseEntity.noContent().build()
    }
}