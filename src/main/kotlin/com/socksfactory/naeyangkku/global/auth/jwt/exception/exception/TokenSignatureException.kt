package com.socksfactory.naeyangkku.global.auth.jwt.exception.exception

import com.socksfactory.naeyangkku.global.auth.jwt.exception.error.JwtErrorCode
import com.socksfactory.naeyangkku.global.exception.BusinessException

object TokenSignatureException : BusinessException(JwtErrorCode.JWT_TOKEN_SIGNATURE_ERROR) {
    private fun readResolve(): Any  = TokenSignatureException
    val EXCEPTION : TokenSignatureException = TokenSignatureException
}
