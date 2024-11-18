package com.socksfactory.naeyangkku.global.auth.jwt

data class JwtInfo (

    val id: Long,

    val name: String,

    val accessToken: String,

    val refreshToken: String

)
