package com.socksfactory.naeyangkku.domain.user.presentation

import com.socksfactory.naeyangkku.domain.user.presentation.dto.request.*
import com.socksfactory.naeyangkku.domain.user.service.UserService
import com.socksfactory.naeyangkku.global.auth.jwt.JwtInfo
import com.socksfactory.naeyangkku.global.common.BaseResponse
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {
    @PostMapping("/register")
    fun registerUser(@RequestBody registerUserRequest: RegisterUserRequest): BaseResponse<Unit> {
        return userService.registerUser(registerUserRequest)
    }

    @PostMapping("/login")
    fun loginUser(@RequestBody loginRequest: LoginRequest): BaseResponse<JwtInfo> {
        return userService.loginUser(loginRequest)
    }

    @PostMapping("/refresh")
    fun refreshUser(@RequestBody refreshRequest: RefreshRequest): BaseResponse<String> {
        return userService.refreshToken(refreshRequest)
    }

    @PostMapping("/login/google")
    fun oAuth2SignIn(@RequestBody @Valid oAuth2SignInRequest: OAuth2SignInRequest): BaseResponse<JwtInfo> {
        return userService.oAuth2SignIn(oAuth2SignInRequest)
    }
}
