package com.socksfactory.naeyangkku.domain.letter.domain.model

import com.socksfactory.naeyangkku.domain.user.domain.entity.UserEntity

data class Letter (
    val id: Long? = null,
    val nickname: String,
    val content: String,
    val ownerId: UserEntity
)
