package com.xiaotianqi.kuaipiao.domain.entity.rbac

import com.xiaotianqi.kuaipiao.domain.entity.user.User
import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "user_roles")
data class UserRole(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = "",

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @ManyToOne
    @JoinColumn(name = "role_id")
    val role: Role,

    @Column(nullable = false)
    val assignedAt: Instant = Instant.now(),

    @Column
    val assignedBy: String? = null
)