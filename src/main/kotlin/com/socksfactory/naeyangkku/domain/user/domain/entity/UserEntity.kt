package com.socksfactory.naeyangkku.domain.user.domain.entity

import com.socksfactory.naeyangkku.domain.user.domain.enums.UserRoles
import jakarta.persistence.*

@Entity
class UserEntity (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val email: String,

    @Column(nullable = false)
    val nickname: String,

    @Column(nullable = false)
    val password: String,

    @Enumerated(EnumType.STRING)
    val role: UserRoles

)
