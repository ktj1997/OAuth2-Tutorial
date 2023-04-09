package spring.oauth.tutorial.auth.adapter.outbound.oauth.client

import com.fasterxml.jackson.databind.ObjectMapper
import java.nio.charset.StandardCharsets
import java.util.Base64
import java.util.Collections
import org.springframework.http.MediaType
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.web.reactive.function.client.WebClient
import spring.oauth.tutorial.auth.adapter.outbound.oauth.client.model.GoogleOAuthUserInfoJwtResponse
import spring.oauth.tutorial.auth.adapter.outbound.oauth.client.model.GoogleOAuthUserInfoResponse
import spring.oauth.tutorial.auth.adapter.outbound.oauth.client.model.GoogleOAuthClientResponse
import spring.oauth.tutorial.auth.applicaiton.outbound.oauth.model.GetOAuthUserInfoQuery
import spring.oauth.tutorial.auth.applicaiton.outbound.oauth.model.OAuthTokenInfo
import spring.oauth.tutorial.auth.applicaiton.outbound.oauth.model.OAuthUserInfo
import spring.oauth.tutorial.auth.domain.OAuthType

class GoogleOAuthClient(
    val objectMapper: ObjectMapper,
    val registration: ClientRegistration
) : OAuthClient {
    override fun getToken(
        authorizationCode: String
    ): OAuthTokenInfo {
        val response =
            WebClient
                .create()
                .post()
                .uri(registration.providerDetails.tokenUri) { uriBuilder ->
                    uriBuilder
                        .queryParam("code", authorizationCode)
                        .queryParam("client_id", registration.clientId)
                        .queryParam("client_secret", registration.clientSecret)
                        .queryParam("redirect_uri", registration.redirectUri)
                        .queryParam("grant_type", registration.authorizationGrantType.value)
                        .build()
                }
                .headers {
                    it.contentType = MediaType.APPLICATION_FORM_URLENCODED
                    it.acceptCharset = Collections.singletonList(StandardCharsets.UTF_8)
                }
                .retrieve()
                .bodyToMono(GoogleOAuthClientResponse::class.java)
                .block() ?: throw IllegalArgumentException("OAuth Authorization Fail: Google")
        return OAuthTokenInfo(
            accessToken = response.accessToken,
            refreshToken = response.refreshToken,
            idToken = response.idToken
        )
    }

    override fun getUserInfo(
        query: GetOAuthUserInfoQuery,
    ): OAuthUserInfo {
        if (query.idToken != null) {

            val base64EncodedPayload = query.idToken.split(".")[1]
            val base64DecodedPayload = Base64.getDecoder().decode(base64EncodedPayload)
            val payload = String(base64DecodedPayload, Charsets.UTF_8)
            val response = objectMapper.readValue(payload, GoogleOAuthUserInfoJwtResponse::class.java)

            return OAuthUserInfo(
                email = response.email
            )
        } else {
            val response =
                WebClient
                    .create()
                    .get()
                    .uri(registration.providerDetails.userInfoEndpoint.uri)
                    .headers {
                        it.contentType = MediaType.APPLICATION_FORM_URLENCODED
                        it.acceptCharset = Collections.singletonList(StandardCharsets.UTF_8)
                        it.setBearerAuth(query.accessToken)
                    }
                    .retrieve()
                    .bodyToMono(GoogleOAuthUserInfoResponse::class.java)
                    .block() ?: throw IllegalArgumentException("OAuth Get UserInfo Fail: Google")
            return OAuthUserInfo(
                email = response.email
            )
        }
    }

    override fun getProvider(): OAuthType {
        return OAuthType.GOOGLE
    }

    override fun getAuthorizationCodeRedirectURI(): String {
        return "${registration.providerDetails.authorizationUri}?access_type=offline&prompt=consent&client_id=${registration.clientId}&redirect_uri=${registration.redirectUri}&response_type=code&scope=${
        registration.scopes.joinToString(
            " "
        )
        }"
    }
}
