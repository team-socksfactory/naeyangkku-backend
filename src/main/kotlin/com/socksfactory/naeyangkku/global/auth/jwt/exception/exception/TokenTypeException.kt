package com.socksfactory.naeyangkku.global.auth.jwt.exception.exception

import com.socksfactory.naeyangkku.global.auth.jwt.exception.error.JwtErrorCode
import com.socksfactory.naeyangkku.global.exception.BusinessException

object TokenTypeException : BusinessException(JwtErrorCode.JWT_TOKEN_ERROR) {

    private fun readResolve(): Any = TokenTypeException

    val EXCEPTION: TokenTypeException = TokenTypeException

}
