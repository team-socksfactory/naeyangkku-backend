package com.socksfactory.naeyangkku.global.auth.jwt.exception.exception

import com.socksfactory.naeyangkku.global.auth.jwt.exception.error.JwtErrorCode
import com.socksfactory.naeyangkku.global.exception.BusinessException

object TokenEmptyException: BusinessException(JwtErrorCode.JWT_EMPTY_EXCEPTION) {
    private fun readResolve():Any = TokenEmptyException
    val EXCEPTION: TokenEmptyException = TokenEmptyException
}
