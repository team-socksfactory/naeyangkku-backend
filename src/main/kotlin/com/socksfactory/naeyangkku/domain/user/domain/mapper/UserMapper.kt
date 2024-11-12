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
            name = entity.name,
            password = entity.password,
            platformType = entity.platformType
        )
    }

    override fun toEntity(domain: User): UserEntity {
        return UserEntity(
            email = domain.email,
            name = domain.name,
            password = domain.password
        )
    }

    fun toDomain(registerUserRequest: RegisterUserRequest, password: String): User {
        return User(
            email = registerUserRequest.email,
            name = registerUserRequest.name,
            password = password,
            platformType = registerUserRequest.platformType
        )
    }
}
