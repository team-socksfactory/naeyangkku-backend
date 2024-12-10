package com.socksfactory.naeyangkku.domain.user.domain.mapper

import com.socksfactory.naeyangkku.domain.user.domain.entity.UserEntity
import com.socksfactory.naeyangkku.domain.user.domain.model.User
import com.socksfactory.naeyangkku.domain.user.presentation.dto.request.RegisterUserRequest
import com.socksfactory.naeyangkku.global.common.Mapper
import org.springframework.stereotype.Component

@Component
class UserMapper(
): Mapper<User, UserEntity> {
    override fun toDomain(entity: UserEntity): User {
        return User(
            id = entity.id,
            email = entity.email,
            nickname = entity.nickname,
            password = entity.password
        )
    }

    override fun toEntity(domain: User): UserEntity {
        return UserEntity(
            email = domain.email,
            nickname = domain.nickname,
            password = domain.password,
            role = domain.role
        )
    }

    fun toDomain(registerUserRequest: RegisterUserRequest, password: String): User {
        return User(
            email = registerUserRequest.email,
            nickname = registerUserRequest.nickname,
            password = password
        )
    }
}
