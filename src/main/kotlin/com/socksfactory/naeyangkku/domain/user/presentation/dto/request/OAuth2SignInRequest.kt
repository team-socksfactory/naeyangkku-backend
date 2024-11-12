package com.socksfactory.naeyangkku.domain.user.presentation.dto.request

import com.socksfactory.naeyangkku.domain.user.domain.enums.PlatformType

data class OAuth2SignInRequest(
    val platformType: PlatformType,
    val code: String,
    val nickname: String?
)
