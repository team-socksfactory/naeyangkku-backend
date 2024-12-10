package com.socksfactory.naeyangkku.global.exception

import com.socksfactory.naeyangkku.global.auth.jwt.exception.error.JwtErrorCode

open class BusinessException(val error: JwtErrorCode) : RuntimeException() {

}
