package com.socksfactory.naeyangkku.global.oauth2

import com.socksfactory.naeyangkku.domain.user.exception.UserErrorCode
import com.socksfactory.naeyangkku.global.exception.CustomException
import com.socksfactory.naeyangkku.global.oauth2.data.response.GoogleOAuth2TokenResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import org.springframework.web.client.toEntity

@Component
class GoogleOAuth2Client(
    private val properties: GoogleOAuth2Properties,
    @Qualifier("google")
    private val restClient: RestClient
) {

    fun getToken(code: String) = restClient.post()
        .uri {
            it.path("token")
                .queryParam("client_id", properties.clientId)
                .queryParam("client_secret", properties.clientSecret)
                .queryParam("code", code)
                .queryParam("grant_type", properties.grantType)
                .queryParam("redirect_uri", properties.redirectUri)
                .build()
        }
        .retrieve()
        .toEntity<GoogleOAuth2TokenResponse>()
        .body ?: throw CustomException(UserErrorCode.INVAILD_OAUTH_CODE)
}
