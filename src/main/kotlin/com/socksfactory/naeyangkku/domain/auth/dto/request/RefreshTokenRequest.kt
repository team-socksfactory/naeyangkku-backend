package com.socksfactory.naeyangkku.domain.auth.dto.request

import jakarta.validation.constraints.NotBlank

data class RefreshTokenRequest(
    @NotBlank
    val refreshToken: String
)
