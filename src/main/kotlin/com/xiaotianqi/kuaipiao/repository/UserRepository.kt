package com.xiaotianqi.kuaipiao.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.xiaotianqi.kuaipiao.domain.entity.user.User
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, String> {
    fun findByEmail(email: String): User?
    fun existsByEmail(email: String): Boolean
    fun existsByUsername(username: String): Boolean
}