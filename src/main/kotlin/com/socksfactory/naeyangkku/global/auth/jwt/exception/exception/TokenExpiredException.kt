package com.project.oauthtemplate.global.security.jwt.exception

import com.project.oauthtemplate.global.exception.BusinessException
import com.socksfactory.naeyangkku.global.auth.jwt.exception.error.JwtErrorCode

object TokenExpiredException : BusinessException(JwtErrorCode.JWT_TOKEN_EXPIRED) {

    private fun readResolve(): Any = TokenExpiredException

    val EXCEPTION: TokenExpiredException = TokenExpiredException

}
