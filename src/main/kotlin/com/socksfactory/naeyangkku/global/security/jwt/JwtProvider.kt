package com.socksfactory.naeyangkku.global.security.jwt

import com.socksfactory.naeyangkku.domain.user.domain.enums.UserRoles
import com.socksfactory.naeyangkku.global.security.jwt.config.JwtProperties
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.*
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

@Component
class JwtProvider (private val jwtProperties: JwtProperties) {

    private val secretKey: SecretKey = SecretKeySpec(
        this.jwtProperties.secretKey.toByteArray(StandardCharsets.UTF_8),
        Jwts.SIG.HS256.key().build().algorithm
    )

    fun generateAccessToken(email: String, roles: UserRoles): String {
        return Jwts.builder()
            .claim("email",email)
            .claim("authority", roles)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + jwtProperties.expiration))
            .signWith(secretKey)
            .compact()
    }

    fun generateRefreshToken(email: String, roles: UserRoles): String {
        return Jwts.builder()
            .claim("email",email)
            .claim("authority", roles)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + jwtProperties.refreshExpiration))
            .signWith(secretKey)
            .compact()
    }
}
