package spring.oauth.tutorial.auth.adapter.outbound.oauth.client

import spring.oauth.tutorial.auth.adapter.outbound.oauth.client.model.OAuthTokenResponse
import org.springframework.security.oauth2.client.registration.ClientRegistration
import spring.oauth.tutorial.auth.applicaiton.outbound.rest.model.OAuthUserInfo
import spring.oauth.tutorial.auth.domain.OAuthType

interface OAuthClient {
    fun getToken(registration: ClientRegistration, authorizationCode: String): OAuthTokenResponse
    fun getUserInfo(accessToken: String, registration: ClientRegistration,): OAuthUserInfo
    fun getProvider(): OAuthType
    fun getAuthorizationCodeRedirectURI(registration: ClientRegistration): String
}
