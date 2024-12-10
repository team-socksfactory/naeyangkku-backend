package com.socksfactory.naeyangkku.global.auth.jwt.exception.exception

import com.socksfactory.naeyangkku.global.auth.jwt.exception.error.JwtErrorCode
import com.socksfactory.naeyangkku.global.exception.BusinessException

object TokenNotSupportException : BusinessException(JwtErrorCode.JWT_TOKEN_UNSUPPORTED_ERROR) {

    private fun readResolve(): Any = TokenNotSupportException

    val EXCEPTION: TokenNotSupportException = TokenNotSupportException

}
