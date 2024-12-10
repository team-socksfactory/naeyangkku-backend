package com.socksfactory.naeyangkku.global.auth.jwt.exception.exception

import com.socksfactory.naeyangkku.global.auth.jwt.exception.error.JwtErrorCode
import com.socksfactory.naeyangkku.global.exception.BusinessException

object TokenUnKnownException: BusinessException(JwtErrorCode.JWT_UNKNOWN_EXCEPTION) {
    private fun readResolve(): Any = TokenUnKnownException
    val EXCEPTION: TokenUnKnownException = TokenUnKnownException
}
