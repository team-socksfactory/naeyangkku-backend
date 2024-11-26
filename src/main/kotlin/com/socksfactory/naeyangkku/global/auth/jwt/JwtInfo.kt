package com.socksfactory.naeyangkku.global.auth.jwt

data class JwtInfo (

    val id: Long,

    val nickname: String,

    val accessToken: String,

    val refreshToken: String

)
