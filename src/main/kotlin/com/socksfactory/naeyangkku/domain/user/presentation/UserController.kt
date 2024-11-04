package com.socksfactory.naeyangkku.domain.user.presentation

import com.socksfactory.naeyangkku.domain.user.presentation.dto.request.LoginRequest
import com.socksfactory.naeyangkku.domain.user.presentation.dto.request.RefreshRequest
import com.socksfactory.naeyangkku.domain.user.presentation.dto.request.RegisterUserRequest
import com.socksfactory.naeyangkku.domain.user.service.UserService
import com.socksfactory.naeyangkku.global.auth.jwt.JwtInfo
import com.socksfactory.naeyangkku.global.common.BaseResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
}
