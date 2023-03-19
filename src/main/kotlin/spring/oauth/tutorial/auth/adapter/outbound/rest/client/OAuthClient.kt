package spring.oauth.tutorial.auth.adapter.outbound.rest.client

import OAuthTokenResponse
import org.springframework.security.oauth2.client.registration.ClientRegistration
import spring.oauth.tutorial.auth.domain.OAuthType

interface OAuthClient {
    fun getToken(registration: ClientRegistration, authorizationCode: String) : OAuthTokenResponse
    fun getProvider(): OAuthType
}