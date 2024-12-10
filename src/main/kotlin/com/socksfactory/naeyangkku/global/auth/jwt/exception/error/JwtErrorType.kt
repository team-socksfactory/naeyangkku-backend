package com.socksfactory.naeyangkku.global.auth.jwt.exception.error

enum class JwtErrorType {
    OK,
    ExpiredJwtException,
    SignatureException,
    MalformedJwtException,
    UnsupportedJwtException,
    IllegalArgumentException,
    UNKNOWN_EXCEPTION,
    JWT_UNKNOWN_EXCEPTION,
    JWT_EMPTY_EXCEPTION,
    JWT_TOKEN_ERROR,
    JWT_ERROR,
}
