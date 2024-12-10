package com.project.oauthtemplate.global.security.jwt.exception

import com.project.oauthtemplate.global.exception.BusinessException
import com.socksfactory.naeyangkku.global.auth.jwt.exception.error.JwtErrorCode

object TokenErrorException : BusinessException(JwtErrorCode.JWT_TOKEN_ERROR) {

    private fun readResolve(): Any = TokenErrorException

    val EXCEPTION: TokenErrorException = TokenErrorException

}
