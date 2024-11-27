package com.socksfactory.naeyangkku.domain.user.domain

import com.socksfactory.naeyangkku.domain.user.domain.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByEmail(email: String): UserEntity?
    fun existsByEmail(email: String): Boolean
    fun existsByNickname(nickname: String): Boolean
}
