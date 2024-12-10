package com.socksfactory.naeyangkku.domain.user.domain.model

import com.socksfactory.naeyangkku.domain.user.domain.entity.UserEntity
import com.socksfactory.naeyangkku.domain.user.domain.enums.UserRoles
import org.springframework.stereotype.Component

data class User(
    val id: Long? = null,
    val email: String = "",
    val nickname: String = "",
    val password: String = "",
    val role: UserRoles = UserRoles.ROLE_USER
) {

    @Component
    companion object{
        fun toUser(userEntity: UserEntity): User {
            return User(
                email = userEntity.email,
                nickname = userEntity.nickname,
                password = userEntity.password,
                role = userEntity.role,
            )
        }

        fun toEntity(user: User): UserEntity {
            return UserEntity(
                email = user.email,
                nickname = user.nickname,
                password = user.password,
                role = user.role
            )
        }
    }

}
