package com.xiaotianqi.kuaipiao.domain.entity.rbac

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator

@Entity
@Table(name = "permissions")
data class Permission(
    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    val id: String = "",

    @Column(nullable = false, unique = true)
    val code: String,

    @Column(nullable = false, unique = true)
    val name: String,

    @Lob
    @Column
    val description: String? = null,

    @ManyToMany(mappedBy = "permissions")
    val roles: Set<Role> = emptySet()
)
