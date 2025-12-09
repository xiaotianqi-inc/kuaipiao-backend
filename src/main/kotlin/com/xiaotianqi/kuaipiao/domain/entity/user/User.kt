package com.xiaotianqi.kuaipiao.domain.entity.user

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import com.xiaotianqi.kuaipiao.domain.entity.rbac.Role
import com.xiaotianqi.kuaipiao.domain.entity.organization.Organization
import com.xiaotianqi.kuaipiao.domain.entity.enterprise.Enterprise
import java.time.Instant

@Entity
@Table(name = "users")
data class User(
    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    val id: String = "",

    @Column(nullable = false, unique = true)
    val username: String,

    @Column(nullable = false)
    val firstName: String,

    @Column(nullable = false)
    val lastName: String,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false)
    val passwordHash: String,

    @Column(nullable = false)
    var emailVerified: Boolean = false,

    @Column(nullable = false)
    var isActive: Boolean = true,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enterprise_id")
    val enterprise: Enterprise? = null,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_organizations",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "organization_id")]
    )
    val organizations: Set<Organization> = emptySet(),

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    val roles: Set<Role> = emptySet(),

    @Column(nullable = false)
    val createdAt: Instant = Instant.now(),

    @Column
    var updatedAt: Instant? = null,

    @Column
    var lastLoginAt: Instant? = null
)