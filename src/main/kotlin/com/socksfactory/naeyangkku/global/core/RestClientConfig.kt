package com.socksfactory.naeyangkku.global.core

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
class RestClientConfig {
    @Bean
    @Qualifier("google")
    fun googleOAuth2RestClient() = RestClient.builder()
        .baseUrl("https://oauth2.googleapis.com")
        .build()
}
