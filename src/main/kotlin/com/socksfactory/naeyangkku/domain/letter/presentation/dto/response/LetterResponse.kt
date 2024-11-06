package com.socksfactory.naeyangkku.domain.letter.presentation.dto.response

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class LetterResponse (
    val id: Long? = null,
    val nickname: String? = null,
    val content: String? = null,
    val ownerId: Long? = null
)
