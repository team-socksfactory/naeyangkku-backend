package com.socksfactory.naeyangkku.domain.letter.presentation.dto

data class CreateLetterRequest(
    val nickname: String = "",
    val content: String = "",
    val ownerId: Long
)
