package com.socksfactory.naeyangkku.domain.user.domain.entity

import com.socksfactory.naeyangkku.domain.user.domain.enums.PlatformType
import com.socksfactory.naeyangkku.domain.user.domain.enums.UserRoles
import com.socksfactory.naeyangkku.domain.user.domain.model.User
import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@Entity
@EntityListeners(AuditingEntityListener::class)
class UserEntity (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val email: String,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val password: String,

    @Column(nullable = false)
    val role: UserRoles = UserRoles.ROLE_USER,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val platformType: PlatformType = PlatformType.DEFAULT
) {
    fun toUser(): User {
        return User(
            id = this.id,
            email = this.email,
            name = this.name,
            password = this.password,
            role = this.role,
            platformType = this.platformType
        )
    }
}
