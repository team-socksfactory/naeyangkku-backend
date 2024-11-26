package com.socksfactory.naeyangkku.domain.user.presentation.dto.request

data class RegisterUserRequest(
    val email: String = "",
    val nickname: String = "",
    val password: String = ""
)
