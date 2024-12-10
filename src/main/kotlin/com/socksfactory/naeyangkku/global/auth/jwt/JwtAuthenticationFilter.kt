package com.socksfactory.naeyangkku.global.auth.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import com.socksfactory.naeyangkku.global.auth.jwt.exception.error.JwtErrorCode
import com.socksfactory.naeyangkku.global.auth.jwt.exception.error.JwtErrorType
import com.socksfactory.naeyangkku.global.common.BaseResponse
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthenticationFilter(
    private val jwtUtils: JwtUtils,
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token: String? = request.getHeader("Authorization")
        val path: String = request.servletPath

        if (path.startsWith("/user/")
        ) {
            filterChain.doFilter(request, response)
            return
        }

        if (token.isNullOrEmpty() || !token.startsWith("Bearer ")) {
            doFilter(request, response, filterChain)
        } else {
            when (jwtUtils.checkTokenInfo(jwtUtils.getToken(token))) {
                JwtErrorType.OK -> {
                    SecurityContextHolder.getContext().authentication = jwtUtils.getAuthentication(token)
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
                    JwtErrorCode.JWT_TOKEN_ILL_EXCEPTION
                )

                JwtErrorType.UNKNOWN_EXCEPTION -> setErrorResponse(response, JwtErrorCode.JWT_UNKNOWN_EXCEPTION)
                JwtErrorType.JWT_UNKNOWN_EXCEPTION -> setErrorResponse(response, JwtErrorCode.JWT_UNKNOWN_EXCEPTION)
                JwtErrorType.JWT_EMPTY_EXCEPTION -> setErrorResponse(response, JwtErrorCode.JWT_EMPTY_EXCEPTION)
                JwtErrorType.JWT_TOKEN_ERROR -> setErrorResponse(response, JwtErrorCode.JWT_TOKEN_ERROR)
                JwtErrorType.JWT_ERROR -> setErrorResponse(response, JwtErrorCode.JWT_TOKEN_ERROR)
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
                BaseResponse<String>(
                    status = errorCode.status.value(),
                    state = errorCode.state,
                    message = errorCode.message
                )
            )
        )
    }
}
