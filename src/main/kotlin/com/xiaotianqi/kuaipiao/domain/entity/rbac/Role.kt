package com.xiaotianqi.kuaipiao.domain.entity.rbac

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import com.xiaotianqi.kuaipiao.domain.entity.user.User

@Entity
@Table(name = "roles")
data class Role(
    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    val id: String = "",

    @Column(nullable = false, unique = true)
    val name: String,

    @Lob
    @Column
    val description: String? = null,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "role_permissions",
        joinColumns = [JoinColumn(name = "role_id")],
        inverseJoinColumns = [JoinColumn(name = "permission_id")]
    )
    val permissions: Set<Permission> = emptySet(),

    @ManyToMany(mappedBy = "roles")
    val users: Set<User> = emptySet()
)
