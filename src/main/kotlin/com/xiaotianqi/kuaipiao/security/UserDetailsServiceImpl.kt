package com.xiaotianqi.kuaipiao.security

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import com.xiaotianqi.kuaipiao.repository.UserRepository

@Service
class UserDetailsServiceImpl(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val user = userRepository.findByEmail(email)
            ?: throw UsernameNotFoundException("User not found with email: $email")

        val authorities = user.roles.flatMap { role ->
            role.permissions.map { permission ->
                SimpleGrantedAuthority(permission.code)
            }
        }

        return org.springframework.security.core.userdetails.User(
            user.email,
            user.passwordHash,
            user.isActive,
            true,
            true,
            true,
            authorities
        )
    }
}
