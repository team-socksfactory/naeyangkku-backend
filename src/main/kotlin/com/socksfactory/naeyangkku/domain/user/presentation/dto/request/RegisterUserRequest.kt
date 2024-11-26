package com.socksfactory.naeyangkku.domain.user.presentation.dto.request

import com.socksfactory.naeyangkku.domain.user.domain.enums.PlatformType

data class RegisterUserRequest(
    val email: String = "",
    val nickname: String = "",
    val password: String = ""
)
