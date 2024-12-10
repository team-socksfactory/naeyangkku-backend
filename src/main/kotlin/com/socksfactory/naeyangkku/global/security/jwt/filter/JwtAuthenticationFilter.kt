package com.socksfactory.naeyangkku.global.security.jwt.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.socksfactory.naeyangkku.global.auth.jwt.exception.error.JwtErrorCode
import com.socksfactory.naeyangkku.global.auth.jwt.exception.error.JwtErrorType
import com.socksfactory.naeyangkku.global.common.BaseResponse
import com.socksfactory.naeyangkku.global.security.jwt.JwtExtract
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val objectMapper: ObjectMapper,
    private val extract: JwtExtract,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token: String? = request.getHeader("Authorization")

        if (token.isNullOrEmpty() || !token.startsWith("Bearer ")) {
            doFilter(request, response, filterChain)
//            setErrorResponse(response, JwtTokenError.JWT_EMPTY_EXCEPTION)
        } else {
            when (extract.checkTokenInfo(extract.getToken(token))) {
                JwtErrorType.OK -> {
                    SecurityContextHolder.getContext().authentication = extract.getAuthentication(token)
                    doFilter(request, response, filterChain)
                }

                JwtErrorType.ExpiredJwtException -> setErrorResponse(response, JwtErrorCode.JWT_TOKEN_EXPIRED)
                JwtErrorType.SignatureException -> setErrorResponse(response, JwtErrorCode.JWT_TOKEN_SIGNATURE_ERROR)
                JwtErrorType.MalformedJwtException -> setErrorResponse(response, JwtErrorCode.JWT_TOKEN_ERROR)
                JwtErrorType.UnsupportedJwtException -> setErrorResponse(
                    response,
                    JwtErrorCode.JWT_TOKEN_UNSUPPORTED_ERROR
                )

                JwtErrorType.IllegalArgumentException -> setErrorResponse(
                    response,
                    JwtErrorCode.JWT_ERROR
                )

                JwtErrorType.UNKNOWN_EXCEPTION -> setErrorResponse(response, JwtErrorCode.JWT_UNKNOWN_EXCEPTION)
                JwtErrorType.JWT_UNKNOWN_EXCEPTION -> TODO()
                JwtErrorType.JWT_EMPTY_EXCEPTION -> TODO()
                JwtErrorType.JWT_TOKEN_ERROR -> TODO()
                JwtErrorType.JWT_ERROR -> TODO()
            }
        }
    }

    private fun setErrorResponse(
        response: HttpServletResponse,
        errorCode: JwtErrorCode
    ) {
        response.status = errorCode.status.value()
        response.contentType = "application/json;charset=UTF-8"

        response.writer.write(
            objectMapper.writeValueAsString(
                BaseResponse<Unit>(
                    status = errorCode.status.value(),
                    message = errorCode.message
                )
            )
        )

    }

}
