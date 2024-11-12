package com.socksfactory.naeyangkku.domain.user.service

import com.socksfactory.naeyangkku.domain.user.presentation.dto.request.*
import com.socksfactory.naeyangkku.global.auth.jwt.JwtInfo
import com.socksfactory.naeyangkku.global.common.BaseResponse

interface UserService {
    fun registerUser(registerUserRequest: RegisterUserRequest): BaseResponse<Unit>
    fun loginUser(loginRequest: LoginRequest): BaseResponse<JwtInfo>
    fun refreshToken(refreshRequest: RefreshRequest): BaseResponse<String>
    fun oAuth2SignIn(req: OAuth2SignInRequest): BaseResponse<JwtInfo>
}
