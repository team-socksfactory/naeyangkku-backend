package com.socksfactory.naeyangkku.domain.letter.domain.model

data class Letter(
    val id: Long? = null,
    val nickname: String,
    val content: String,
    val iconNm: Long,
    val ownerId: Long,
    val ownerNickname: String
)
