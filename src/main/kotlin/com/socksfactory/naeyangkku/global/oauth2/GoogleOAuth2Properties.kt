package com.socksfactory.naeyangkku.global.oauth2

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class GoogleOAuth2Properties(
    @Value("\${spring.security.oauth2.google.client-id}") val clientId: String,
    @Value("\${spring.security.oauth2.google.client-secret}") val clientSecret: String,
    @Value("\${spring.security.oauth2.google.redirect-uri}") val redirectUri: String,
    @Value("\${spring.security.oauth2.google.token-uri}") val tokenUri: String,
    @Value("\${spring.security.oauth2.google.resource-uri}") val resourceUri: String,
    @Value("\${spring.security.oauth2.google.grant-type}") val grantType: String,
)
