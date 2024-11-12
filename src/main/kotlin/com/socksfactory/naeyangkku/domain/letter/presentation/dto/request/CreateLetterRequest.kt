package com.socksfactory.naeyangkku.domain.letter.presentation.dto.request

data class CreateLetterRequest(
    val nickname: String = "",
    val content: String = "",
    val iconNm: Long = 0,
    val ownerId: Long = 0
)
