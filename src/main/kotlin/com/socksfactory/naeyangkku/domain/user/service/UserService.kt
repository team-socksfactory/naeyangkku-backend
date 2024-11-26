package com.socksfactory.naeyangkku.domain.user.service

import com.socksfactory.naeyangkku.domain.user.presentation.dto.request.LoginRequest
import com.socksfactory.naeyangkku.domain.user.presentation.dto.request.RefreshRequest
import com.socksfactory.naeyangkku.domain.user.presentation.dto.request.RegisterUserRequest
import com.socksfactory.naeyangkku.global.auth.jwt.JwtInfo
import com.socksfactory.naeyangkku.global.common.BaseResponse

interface UserService {
    fun registerUser(registerUserRequest: RegisterUserRequest): BaseResponse<Unit>
    fun loginUser(loginRequest: LoginRequest): BaseResponse<JwtInfo>
    fun refreshToken(refreshRequest: RefreshRequest): BaseResponse<String>
}
