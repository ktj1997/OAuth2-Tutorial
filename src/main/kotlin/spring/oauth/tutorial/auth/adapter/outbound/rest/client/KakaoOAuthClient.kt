package spring.oauth.tutorial.auth.adapter.outbound.rest.client

import KaKaoOauthClientResponse
import OAuthTokenResponse
import org.springframework.http.MediaType
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import spring.oauth.tutorial.auth.adapter.outbound.rest.client.model.KakaoOauthUserInfoResponse
import spring.oauth.tutorial.auth.domain.OAuthType
import java.nio.charset.StandardCharsets
import java.util.Collections
import spring.oauth.tutorial.auth.applicaiton.outbound.rest.model.OAuthUserInfo

@Component
class KakaoOAuthClient : OAuthClient {
    override fun getToken(
        registration: ClientRegistration,
        authorizationCode: String
    ): OAuthTokenResponse {
        val response =
            WebClient
                .create()
                .post()
                .uri(registration.providerDetails.tokenUri) { uriBuilder ->
                    uriBuilder
                        .queryParam("code", authorizationCode)
                        .queryParam("client_id", registration.clientId)
                        .queryParam("redirect_uri", registration.redirectUri)
                        .queryParam("grant_type", registration.authorizationGrantType.value)
                        .build()
                }
                .headers {
                    it.contentType = MediaType.APPLICATION_FORM_URLENCODED
                    it.acceptCharset = Collections.singletonList(StandardCharsets.UTF_8)
                }
                .retrieve()
                .bodyToMono(KaKaoOauthClientResponse::class.java)
                .block() ?: throw IllegalArgumentException("OAuth Authorization Fail: Kakao")

        return OAuthTokenResponse(response.accessToken)
    }

    override fun getUserInfo(accessToken: String, registration: ClientRegistration,): OAuthUserInfo {
        val response =
            WebClient
                .create()
                .get()
                .uri(registration.providerDetails.userInfoEndpoint.uri)
                .headers {
                    it.contentType = MediaType.APPLICATION_FORM_URLENCODED
                    it.acceptCharset = Collections.singletonList(StandardCharsets.UTF_8)
                    it.setBearerAuth(accessToken)
                }
                .retrieve()
                .bodyToMono(KakaoOauthUserInfoResponse::class.java)
                .block() ?: throw IllegalArgumentException("OAuth Get UserInfo Fail: Kakao")
        return OAuthUserInfo(
            email = response.kakaoAccount?.email ?: ""
        )
    }

    override fun getProvider(): OAuthType {
        return OAuthType.KAKAO
    }
}
