package com.project.oauthtemplate.global.security.jwt.exception

import com.project.oauthtemplate.global.exception.BusinessException
import com.socksfactory.naeyangkku.global.auth.jwt.exception.error.JwtErrorCode

object TokenNotSupportException : BusinessException(JwtErrorCode.JWT_TOKEN_UNSUPPORTED_ERROR) {

    private fun readResolve(): Any = TokenNotSupportException

    val EXCEPTION: TokenNotSupportException = TokenNotSupportException

}
