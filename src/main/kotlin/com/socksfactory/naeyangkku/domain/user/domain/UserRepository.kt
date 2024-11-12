package com.socksfactory.naeyangkku.domain.user.domain

import com.socksfactory.naeyangkku.domain.user.domain.entity.UserEntity
import com.socksfactory.naeyangkku.domain.user.exception.UserErrorCode
import com.socksfactory.naeyangkku.global.exception.CustomException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByEmail(email: String): UserEntity?
    fun existsByEmail(email: String): Boolean
    fun findByUsername(username: String): List<UserEntity>
}

fun UserRepository.getByUsername(username: String): UserEntity =
    findByUsername(username).firstOrNull() ?: throw CustomException(UserErrorCode.USER_NOT_FOUND)
