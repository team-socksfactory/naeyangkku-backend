package com.socksfactory.naeyangkku.global.config.redis

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
data class RedisProperties(
    @Value("\${spring.data.redis.host}") val host: String,
    @Value("\${spring.data.redis.port}") val port: Int
)
