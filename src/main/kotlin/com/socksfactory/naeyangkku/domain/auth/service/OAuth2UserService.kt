package com.socksfactory.naeyangkku.domain.auth.service

import com.socksfactory.naeyangkku.domain.auth.dto.request.AuthenticationRequest
import com.socksfactory.naeyangkku.domain.auth.dto.request.RefreshTokenRequest
import com.socksfactory.naeyangkku.domain.auth.dto.response.JsonWebTokenResponse
import com.socksfactory.naeyangkku.domain.auth.dto.response.RefreshTokenResponse
import com.socksfactory.naeyangkku.domain.user.domain.UserRepository
import com.socksfactory.naeyangkku.domain.user.domain.model.User
import com.socksfactory.naeyangkku.domain.user.exception.UserErrorCode
import com.socksfactory.naeyangkku.global.auth.jwt.exception.error.JwtErrorType
import com.socksfactory.naeyangkku.global.auth.jwt.exception.exception.TokenExpiredException
import com.socksfactory.naeyangkku.global.exception.CustomException
import com.socksfactory.naeyangkku.global.infra.google.dto.OAuth2Attribute
import com.socksfactory.naeyangkku.global.infra.google.service.GoogleService
import com.socksfactory.naeyangkku.global.security.jwt.JwtExtract
import com.socksfactory.naeyangkku.global.security.jwt.JwtProvider
import io.jsonwebtoken.lang.Strings
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class OAuth2UserService(
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder,
    private val googleService: GoogleService,
    private val jwtExtract: JwtExtract,
    private val jwtProvider: JwtProvider
) {

    fun auth(request: AuthenticationRequest): JsonWebTokenResponse {
        val oAuth2Attribute: OAuth2Attribute = googleService.getTokenInfo(request.idToken)
        if (!Strings.hasText(oAuth2Attribute.email)) {
            CustomException(UserErrorCode.USER_NOT_FOUND)
        }
        val user = findOrSave(oAuth2Attribute)
        return JsonWebTokenResponse(
            jwtProvider.generateAccessToken(user.email, user.role),
            jwtProvider.generateRefreshToken(user.email, user.role)
        )
    }

    fun refresh(request: RefreshTokenRequest): RefreshTokenResponse {
        val got = jwtExtract.getToken(request.refreshToken)
        val user = jwtExtract.findUserByEmail(got)
        if (jwtExtract.checkTokenInfo(got) == JwtErrorType.ExpiredJwtException) {
            throw TokenExpiredException.EXCEPTION
        }
        return RefreshTokenResponse(
            jwtProvider.generateAccessToken(user.email, user.role),
        )
    }

    private fun findOrSave(oAuth2Attribute: OAuth2Attribute): User {
        val user: User = userRepository.findUserByEmail(oAuth2Attribute.email)
                .map { userEntity -> User.toUser(userEntity) }
                .orElseThrow { CustomException(UserErrorCode.USER_NOT_FOUND) }
        return user
    }

    fun save(request: User){
        userRepository.save(User.toEntity(User(
            email = request.email,
            nickname = request.nickname,
            password = encoder.encode(request.password),
            role = request.role
        )))
    }
}
