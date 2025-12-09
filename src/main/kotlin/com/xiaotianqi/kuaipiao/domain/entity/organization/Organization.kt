package com.xiaotianqi.kuaipiao.domain.entity.organization

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import java.time.Instant
import com.xiaotianqi.kuaipiao.enums.EntityStatus
import com.xiaotianqi.kuaipiao.domain.entity.user.User

@Entity
@Table(name = "organizations")
data class Organization(
    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    val id: String = "",

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val code: String,

    @Column(nullable = false)
    val address: String,

    @Column(nullable = false)
    val phone: String,

    @Column(nullable = false)
    val email: String,

    @Column(nullable = false)
    val country: String,

    @Column(nullable = false)
    val city: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: EntityStatus,

    @Lob
    @Column
    val metadata: String? = null,

    @ManyToMany(mappedBy = "organizations", fetch = FetchType.EAGER)
    val users: Set<User> = emptySet(),

    @Column(name = "created_at", nullable = false)
    val createdAt: Instant = Instant.now(),

    @Column(name = "updated_at")
    var updatedAt: Instant? = null
)
