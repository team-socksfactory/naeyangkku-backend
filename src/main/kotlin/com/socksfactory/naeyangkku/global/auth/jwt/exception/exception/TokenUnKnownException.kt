package com.project.oauthtemplate.global.security.jwt.exception

import com.project.oauthtemplate.global.exception.BusinessException
import com.socksfactory.naeyangkku.global.auth.jwt.exception.error.JwtErrorCode

object TokenUnKnownException: BusinessException(JwtErrorCode.JWT_UNKNOWN_EXCEPTION) {
    private fun readResolve(): Any = TokenUnKnownException
    val EXCEPTION: TokenUnKnownException = TokenUnKnownException
}
