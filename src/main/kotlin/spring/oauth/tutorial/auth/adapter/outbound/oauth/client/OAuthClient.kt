package spring.oauth.tutorial.auth.adapter.outbound.oauth.client

import org.springframework.security.oauth2.client.registration.ClientRegistration
import spring.oauth.tutorial.auth.applicaiton.outbound.oauth.model.GetOAuthUserInfoQuery
import spring.oauth.tutorial.auth.applicaiton.outbound.oauth.model.OAuthTokenInfo
import spring.oauth.tutorial.auth.applicaiton.outbound.oauth.model.OAuthUserInfo
import spring.oauth.tutorial.auth.domain.OAuthType

interface OAuthClient {
    fun getToken(registration: ClientRegistration, authorizationCode: String): OAuthTokenInfo
    fun getUserInfo(query: GetOAuthUserInfoQuery, registration: ClientRegistration): OAuthUserInfo
    fun getProvider(): OAuthType
    fun getAuthorizationCodeRedirectURI(registration: ClientRegistration): String
}
