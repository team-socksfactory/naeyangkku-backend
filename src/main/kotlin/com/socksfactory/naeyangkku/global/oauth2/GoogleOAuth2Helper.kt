package com.socksfactory.naeyangkku.global.oauth2

import com.socksfactory.naeyangkku.global.exception.CustomException
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import com.socksfactory.naeyangkku.domain.user.exception.UserErrorCode
import org.springframework.stereotype.Component

@Component
class GoogleOAuth2Helper(
    private val properties: GoogleOAuth2Properties,
) {

    fun verifyIdToken(idToken: String): GoogleIdToken {
        val verifier = GoogleIdTokenVerifier
            .Builder(NetHttpTransport(), GsonFactory())
            .setAudience(
                listOf(properties.clientId)
            )
            .build()

        return verifier.verify(idToken)
            ?: throw CustomException(UserErrorCode.INVAILD_ID_TOKEN)
    }
}
