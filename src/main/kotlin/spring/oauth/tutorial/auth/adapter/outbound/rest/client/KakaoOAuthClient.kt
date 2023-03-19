package spring.oauth.tutorial.auth.adapter.outbound.rest.client

import KaKaoOauthClientRequest
import KaKaoOauthClientResponse
import OAuthTokenResponse
import java.nio.charset.StandardCharsets
import java.util.Collections
import org.springframework.http.MediaType
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import spring.oauth.tutorial.auth.domain.OAuthType

@Component
class KakaoOAuthClient : OAuthClient {
    override fun getToken(
        registration: ClientRegistration,
        authorizationCode: String
    ): OAuthTokenResponse {
        val requestBody = KaKaoOauthClientRequest(
            code = authorizationCode,
            clientId = registration.clientId,
            clientSecret = registration.clientSecret,
            redirectUri = registration.redirectUri,
            grantType = registration.authorizationGrantType.value
        )
        val response =
            WebClient
                .create()
                .post()
                .uri (registration.providerDetails.tokenUri) { uriBuilder ->
                    uriBuilder
                        .queryParam("code", authorizationCode)
                        .queryParam("client_id", registration.clientId)
                        .queryParam("redirect_uri", registration.redirectUri)
                        .queryParam("grant_type", registration.authorizationGrantType.value)
                        .build()
                }
                .headers{
                    it.contentType = MediaType.APPLICATION_FORM_URLENCODED
                    it.acceptCharset = Collections.singletonList(StandardCharsets.UTF_8)
                }
                .retrieve()
                .bodyToMono(KaKaoOauthClientResponse::class.java)
                .block() ?: throw IllegalArgumentException("OAuth Authorization Fail: Kakao")

        return OAuthTokenResponse(response.accessToken)
    }

    override fun getProvider(): OAuthType {
        return OAuthType.KAKAO
    }
}