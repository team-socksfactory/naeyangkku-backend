package com.project.oauthtemplate.global.security.jwt.exception

import com.project.oauthtemplate.global.exception.BusinessException
import com.socksfactory.naeyangkku.global.auth.jwt.exception.error.JwtErrorCode

object TokenEmptyException: BusinessException(JwtErrorCode.JWT_EMPTY_EXCEPTION) {
    private fun readResolve():Any = TokenEmptyException
    val EXCEPTION: TokenEmptyException = TokenEmptyException
}
