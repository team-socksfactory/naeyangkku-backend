package com.socksfactory.naeyangkku.domain.user.domain

import com.socksfactory.naeyangkku.domain.user.domain.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByEmail(email: String): UserEntity?
    fun findUserByEmail(email: String): Optional<UserEntity>
    fun findByNickname(nickname: String): UserEntity?
    fun existsByEmail(email: String): Boolean
    fun existsByNickname(nickname: String): Boolean
}
