package com.project.oauthtemplate.global.security.jwt.exception

import com.project.oauthtemplate.global.exception.BusinessException
import com.socksfactory.naeyangkku.global.auth.jwt.exception.error.JwtErrorCode

object TokenSignatureException : BusinessException(JwtErrorCode.JWT_TOKEN_SIGNATURE_ERROR) {
    private fun readResolve(): Any  = TokenSignatureException
    val EXCEPTION : TokenSignatureException = TokenSignatureException
}
