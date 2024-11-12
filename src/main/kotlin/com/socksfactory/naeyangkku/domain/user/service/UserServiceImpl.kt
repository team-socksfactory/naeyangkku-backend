package com.socksfactory.naeyangkku.domain.user.service

import com.socksfactory.naeyangkku.domain.user.domain.UserRepository
import com.socksfactory.naeyangkku.domain.user.domain.entity.UserEntity
import com.socksfactory.naeyangkku.domain.user.domain.enums.PlatformType
import com.socksfactory.naeyangkku.domain.user.domain.mapper.UserMapper
import com.socksfactory.naeyangkku.domain.user.domain.model.User
import com.socksfactory.naeyangkku.domain.user.exception.UserErrorCode
import com.socksfactory.naeyangkku.domain.user.presentation.dto.request.*
import com.socksfactory.naeyangkku.global.auth.jwt.JwtInfo
import com.socksfactory.naeyangkku.global.auth.jwt.JwtUtils
import com.socksfactory.naeyangkku.global.auth.jwt.exception.JwtErrorCode
import com.socksfactory.naeyangkku.global.auth.jwt.exception.type.JwtErrorType
import com.socksfactory.naeyangkku.global.common.BaseResponse
import com.socksfactory.naeyangkku.global.exception.CustomException
import com.socksfactory.naeyangkku.global.oauth2.GoogleOAuth2Client
import com.socksfactory.naeyangkku.global.oauth2.GoogleOAuth2Helper
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
    private val bytePasswordEncoder: BCryptPasswordEncoder,
    private val googleOAuth2Client: GoogleOAuth2Client,
    private val googleOAuth2Helper: GoogleOAuth2Helper,
    private val jwtUtils: JwtUtils
) : UserService {

    @Transactional
    override fun registerUser(registerUserRequest: RegisterUserRequest): BaseResponse<Unit> {

        if(userRepository.existsByEmail(registerUserRequest.email)) throw CustomException(UserErrorCode.USER_ALREADY_EXIST)

        userRepository.save(
            userMapper.toEntity(
                userMapper.toDomain(registerUserRequest, bytePasswordEncoder.encode(registerUserRequest.password.trim()))
            )
        )

        return BaseResponse(
            message = "회원가입 성공"
        )

    }

    @Transactional(readOnly = true)
    override fun loginUser(loginRequest: LoginRequest): BaseResponse<JwtInfo> {

        val user = userRepository.findByEmail(loginRequest.email)?: throw CustomException(UserErrorCode.USER_NOT_FOUND)

        if (bytePasswordEncoder.matches(user.password, loginRequest.password)) throw CustomException(UserErrorCode.USER_NOT_MATCH)

        return BaseResponse(
            message = "로그인 성공",
            data = jwtUtils.generate(
                user = userMapper.toDomain(user)
            )
        )

    }

    @Transactional(readOnly = true)
    override fun refreshToken(refreshRequest: RefreshRequest): BaseResponse<String> {
        val token = jwtUtils.getToken(refreshRequest.refreshToken)

        if (jwtUtils.checkTokenInfo(token) == JwtErrorType.ExpiredJwtException) {
            throw CustomException(JwtErrorCode.JWT_TOKEN_EXPIRED)
        }

        val user = userRepository.findByEmail(
            jwtUtils.getUsername(token)
        )

        return BaseResponse (
            message = "리프레시 성공",
            data = jwtUtils.refreshToken(
                user = userMapper.toDomain(user!!)
            )
        )
    }

    @Transactional
    override fun oAuth2SignIn(oauthRequest: OAuth2SignInRequest): BaseResponse<JwtInfo> {
        val token = when (oauthRequest.platformType) {
            PlatformType.GOOGLE -> googleSignIn(oauthRequest)
            else -> throw CustomException(UserErrorCode.INVAILD_PLATFORM_TYPE)
        }
        return BaseResponse (
            message = "로그인 성공",
            data = jwtUtils.generate(token)
        )
    }

    private fun googleSignIn(oauthRequest: OAuth2SignInRequest): User {
        val token = googleOAuth2Client.getToken(code = oauthRequest.code)

        val idToken = googleOAuth2Helper.verifyIdToken(idToken = token.idToken)
        val username = idToken.payload.email
        val users = userRepository.findByUsername(username)
        val user = users.firstOrNull() ?: userRepository.save(
            UserEntity(
                email = username,
                password = null.toString(),
                name = idToken.payload["name"] as? String ?: "유저",
                platformType = oauthRequest.platformType
            )
        )
        return user.toUser();
    }

}
