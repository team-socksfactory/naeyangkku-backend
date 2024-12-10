package com.socksfactory.naeyangkku.global.auth.jwt.exception.exception

import com.socksfactory.naeyangkku.global.auth.jwt.exception.error.JwtErrorCode
import com.socksfactory.naeyangkku.global.exception.BusinessException

object TokenExpiredException : BusinessException(JwtErrorCode.JWT_TOKEN_EXPIRED) {

    private fun readResolve(): Any = TokenExpiredException

    val EXCEPTION: TokenExpiredException = TokenExpiredException

}
