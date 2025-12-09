package com.xiaotianqi.kuaipiao.domain.dto.auth

import com.xiaotianqi.kuaipiao.domain.dto.organization.OrganizationRequest
import com.xiaotianqi.kuaipiao.domain.entity.user.User
import com.xiaotianqi.kuaipiao.validation.Validatable
import com.xiaotianqi.kuaipiao.validation.RegexPatterns
import io.konform.validation.Validation
import io.konform.validation.ValidationResult
import io.konform.validation.constraints.maxLength
import io.konform.validation.constraints.minLength
import io.konform.validation.constraints.pattern
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size
import java.time.Instant

data class LoginRequest(
    @field:NotBlank(message = "Email is required")
    @field:Email(message = "Invalid email format")
    val email: String,

    @field:NotBlank(message = "Password is required")
    val password: String
)

data class RegistrationRequest(
    val username: String? = null,
    @field:NotBlank(message = "First name is required")
    val firstName: String,
    @field:NotBlank(message = "Last name is required")
    val lastName: String,
    val enterpriseId: String? = null,
    val organization: OrganizationRequest? = null,
    @field:NotBlank(message = "Email is required")
    @field:Email(message = "Invalid email format")
    val email: String,
    @field:NotBlank(message = "Password is required")
    @field:Size(min = 8, message = "Password must be at least 8 characters")
    val password: String,
) : Validatable<RegistrationRequest> {
    override fun validate(): ValidationResult<RegistrationRequest> =
        Validation {
            RegistrationRequest::email {
                pattern(RegexPatterns.emailPattern) hint "Please provide a valid email address"
            }
            RegistrationRequest::password {
                minLength(8) hint "Password min length is 8 characters"
                maxLength(100) hint "Password max length is 100 characters"
                pattern(RegexPatterns.passwordPatterns) hint "Password needs at least an uppercase character, a lowercase one and a number"
            }
        }.invoke(this)
}

data class SuperAdminCreateRequest(
    val username: String? = null,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
)

data class AdminRegisterRequest(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val username: String? = null
)

data class MessageResponse(
    val message: String
)

data class AuthResponse(
    val token: String,
    val user: UserResponse
)

data class AdminResponse(
    val message: String? = null,
    val token: String? = null,
    val user: UserDetailDto? = null,
    val error: String? = null
)

data class UserResponse(
    val id: String,
    val email: String,
    val username: String,
    val firstName: String,
    val lastName: String,
    val emailVerified: Boolean,
    val enterpriseId: String?,
    val organizationIds: List<String>,
    val roleIds: List<String>,
    val createdAt: Instant,
    val updatedAt: Instant?
) {
    companion object {
        fun from(user: User) = UserResponse(
            id = user.id,
            email = user.email,
            username = user.username,
            firstName = user.firstName,
            lastName = user.lastName,
            emailVerified = user.emailVerified,
            enterpriseId = user.enterprise?.id,
            organizationIds = user.organizations.map { it.id }.toList(),
            roleIds = user.roles.map { it.id }.toList(),
            createdAt = user.createdAt,
            updatedAt = user.updatedAt
        )
    }
}

data class UserDetailDto(
    val id: String,
    val email: String,
    val username: String,
    val firstName: String,
    val lastName: String,
    val isActive: Boolean,
    val emailVerified: Boolean,
    val roleIds: List<String>,
    val createdAt: Instant
) {
    companion object {
        fun from(user: User) = UserDetailDto(
            id = user.id,
            email = user.email,
            username = user.username,
            firstName = user.firstName,
            lastName = user.lastName,
            isActive = user.isActive,
            emailVerified = user.emailVerified,
            roleIds = user.roles.map { it.id }.toList(),
            createdAt = user.createdAt
        )
    }
}

data class UsersResponse(
    val users: List<UserDetailDto>,
    val page: Int,
    val limit: Int,
    val total: Long
)

data class UserData(
    val id: String,
    val username: String? = null,
    val firstName: String,
    val lastName: String,
    val email: String,
    val emailVerified: Boolean,
    val passwordHash: String,
    val enterpriseId: String? = null,
    val organizationIds: List<String> = emptyList(),
    val roleIds: List<String> = emptyList(),
    val isActive: Boolean = true,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant? = null,
    val lastLoginAt: Instant? = null,
)

data class UserCreateData(
    val id: String,
    val username: String? = null,
    val firstName: String,
    val lastName: String,
    val email: String,
    val emailVerified: Boolean,
    val passwordHash: String,
    val enterpriseId: String? = null,
    val organizationIds: List<String> = emptyList(),
    val roleIds: List<String> = emptyList(),
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant? = null,
)

data class UserAuthSessionData(
    val id: String,
    val userId: String,
    val iat: Long,
    val deviceName: String?,
    val ip: String,
    val token: String,
    val roles: List<String> = emptyList(),
    val permissions: List<String> = emptyList()
)

data class UserSessionData(
    val sessionId: String,
    val userId: String,
    val token: String,
    val roles: List<String> = emptyList(),
    val permissions: List<String> = emptyList()
)

data class UserSessionCookie(
    val sessionId: String,
    val userId: String
)

data class UpdateStatusRequest(
    val isActive: Boolean
)

data class PasswordResetRequest(
    @field:NotBlank(message = "Password is required")
    @field:Size(min = 8, message = "Password must be at least 8 characters")
    val password: String
)