package com.project.oauthtemplate.global.security.jwt.exception

import com.project.oauthtemplate.global.exception.BusinessException
import com.socksfactory.naeyangkku.global.auth.jwt.exception.error.JwtErrorCode

object TokenTypeException : BusinessException(JwtErrorCode.JWT_TOKEN_ERROR) {

    private fun readResolve(): Any = TokenTypeException

    val EXCEPTION: TokenTypeException = TokenTypeException

}
